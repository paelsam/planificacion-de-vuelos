import Datos.Vuelo
import Datos.Aeropuerto
import Datos.Itinerario

package object Itinerarios {

  // Convertir horas a GMT
  def obtenerGMT(aeropuertos: List[Aeropuerto], aeropuerto: String): Int = {
    // Dividir entre 100 para obtener la parte entera
    (aeropuertos.find(_.Cod == aeropuerto).get.GMT / 100).toInt
  }

  def obtenerTiempoVuelo( aeropuertos: List[Aeropuerto], itinerario: Itinerario ): Int = {
    
    // Hacer un foldRight de todos los vuelos
    itinerario.foldRight(0)((vuelo, acc) => {
      // Del vuelo inicial obtener la hora de salida en GMT
      val vOrgGMT = obtenerGMT(aeropuertos, vuelo.Org)
      val HSvGMT = if (vuelo.HS - vOrgGMT < 0) (vuelo.HS - vOrgGMT + 24) else (vuelo.HS - vOrgGMT)

      // Del vuelo final obtener la hora de llegada en GMT
      val vDstGMT = obtenerGMT(aeropuertos, vuelo.Dst)
      val HLvGMT = if (vuelo.HL - vDstGMT < 0) (vuelo.HL - vDstGMT + 24) else (vuelo.HL - vDstGMT)

      // Sumar al acumulador la diferencia de horas 
      // Se suman 24 * 60 minutos en caso de que el resultado sea menor que 0 
      val diferenciaHvGMT = ((HLvGMT * 60 + vuelo.ML) - (HSvGMT * 60 + vuelo.MS))
      if ( diferenciaHvGMT < 0 ) acc +  diferenciaHvGMT + (24 * 60) else acc + diferenciaHvGMT
    })
  }

  def obtenerTiempoEspera( aeropuertos: List[Aeropuerto], itinerario: Itinerario,acc: Int ): Int = {
    itinerario match {
      // En caso de que el itinerario es vacío, devuelve el acumulador
      case Nil          => acc
      case vuelo :: Nil => acc
      // En caso de que el itinerario no sea vacío, se recorre el resto de itinerarios
      case vuelo1 :: vuelo2 :: tail => {
        val v1DstGMT = obtenerGMT(aeropuertos, vuelo1.Dst) // GMT del destino del vuelo1
        // Del vuelo1 obtener la hora de llegada en GMT
        val HLv1GMT = if (vuelo1.HL - v1DstGMT  < 0) (vuelo1.HL - v1DstGMT + 24) else (vuelo1.HL - v1DstGMT)

        // Del vuelo2 obtener la hora de salida en GMT
        val v2OrgGMT = obtenerGMT(aeropuertos, vuelo2.Org) // GMT del origen del vuelo2
        val HSv2GMT = if (vuelo2.HS - v2OrgGMT < 0) (vuelo2.HS - v2OrgGMT + 24) else (vuelo2.HS - v2OrgGMT)

        val diferenciaHvGMT = (HSv2GMT * 60 + vuelo2.MS) - (HLv1GMT * 60 + vuelo1.ML) // Diferencias de horas en GMT

        obtenerTiempoEspera( aeropuertos, vuelo2 :: tail, acc + diferenciaHvGMT)
      }
    }
  }

  def itinerarios( vuelos: List[Vuelo],aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, dos códigos de aeropuerto
    // y devuelve todos los itinerarios entre esos dos aeropuertos

    // Se deben considerar todos los itinerarios posibles, sin importar
    // Es decir, la ruta más corta, la ruta con menos escalas, la ruta con menos tiempo en el aire, etc.

    (a: String, b: String) => {
        vuelos
          .filter(_.Org == a)
          .flatMap { vuelo =>
            if (vuelo.Dst == b) List(List(vuelo))
            else {
              itinerarios(vuelos, aeropuertos)(vuelo.Dst, b).map(vuelo :: _)
            }
          }
      }

  }

  def itinerariosTiempo( vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve una función que devuelve los tres (si los hay) itinerarios que minimizan el
    // tiempo de viaje entre esos dos aeropuertos

    // Los tiempos están en hora local del aeropuerto de origen
    // Entonces hay que convetirlos a horas en GMT
    (a: String, b: String) => {

        // Función que obtiene el tiempo total de un itinerario
        def obtenerTiempoTotal(itinerario: Itinerario): Int = {
          obtenerTiempoVuelo(aeropuertos, itinerario) + obtenerTiempoEspera(aeropuertos,itinerario, 0)
        }

        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) => { obtenerTiempoTotal(a) < obtenerTiempoTotal(b) })
          .take(3)
      }
  }

  def itinerariosEscalas( vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan el numero
    // de cambios de avión entre esos dos aeropuertos

    (a: String, b: String) => {

        def calcularEscalasTotales(itinerario: Itinerario): Int = {
          itinerario.map(_.Esc).sum + (itinerario.length - 1)
        }

        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) => calcularEscalasTotales(a) < calcularEscalasTotales(b))
          .take(3)
      }
  }

  def itinerariosAire( vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan
    // el tiempo en el aire entre esos dos aeropuertos

    (a: String, b: String) => {
        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) => obtenerTiempoVuelo(aeropuertos, a) < obtenerTiempoVuelo(aeropuertos,b))
          .take(3)
      }
  }

  def itinerarioSalida( vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String, Int, Int) => Itinerario = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto, y h:m una hora de la cita en c2
    // y devuelve el itinerario que optimiza la hora de salida para llegar a tiempo a la cita

    (cod1: String, cod2: String, h: Int, m: Int) => {

        val posiblesResultados = for {
          itinerario <- itinerarios(vuelos, aeropuertos)(cod1, cod2)
          vuelo <- itinerario
          if ((vuelo.HL - obtenerGMT(aeropuertos,vuelo.Dst)) * 60 + vuelo.ML <= ((h - obtenerGMT(aeropuertos, vuelo.Dst)) * 60 + m))
        } yield itinerario

        posiblesResultados.minBy {
          itinerario => 
            val horaLlegada = math.abs((itinerario.last.HL - obtenerGMT(aeropuertos, itinerario.last.Dst))) * 60 + itinerario.last.ML
            val diferenciaHoras = math.abs(horaLlegada - ((h - obtenerGMT(aeropuertos, itinerario.last.Dst)) * 60 + m))
            (obtenerTiempoVuelo(aeropuertos, itinerario) + obtenerTiempoEspera(aeropuertos, itinerario, 0), diferenciaHoras)
        } 
      }
  }
}
