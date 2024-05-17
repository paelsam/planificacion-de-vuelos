import Datos.Vuelo
import Datos.Aeropuerto
import Datos.Itinerario
import Datos.aeropuertos

/*
  case class Aeropuerto(Cod: String, X: Int, Y: Int, GMT: Int)

  case class Vuelo(
      Aln: String,
      Num: Int,
      Org: String,
      HS: Int,
      MS: Int,
      Dst: String,
      HL: Int,
      ML: Int,
      Esc: Int
  )

  type Itinerario = List[Vuelo]
 */

package object Itinerarios {

  // Convertir horas a GMT
  def obtenerGMT(aeropuertos: List[Aeropuerto], aeropuerto: String): Int = {
    // Dividir entre 100 para obtener la parte entera
    (aeropuertos.find(_.Cod == aeropuerto).get.GMT / 100).toInt
  }

  def obtenerTiempoVuelo(
      aeropuertos: List[Aeropuerto],
      itinerario: Itinerario
  ): Int = {
    // Hacer un for de todos los vuelos
    itinerario.foldRight(0)((vuelo, acc) => {
      // Del vuelo obtener la hora de salida en GMT
      val HSvGMT =
        if (vuelo.HS - obtenerGMT(aeropuertos, vuelo.Org) < 0)
          (vuelo.HS - obtenerGMT(aeropuertos, vuelo.Org) + 24)
        else (vuelo.HS - obtenerGMT(aeropuertos, vuelo.Org))
      // Del vuelo obtener la hora de llegada en GMT
      val HLvGMT =
        if (vuelo.HL - obtenerGMT(aeropuertos, vuelo.Dst) < 0)
          (vuelo.HL - obtenerGMT(aeropuertos, vuelo.Dst) + 24)
        else (vuelo.HL - obtenerGMT(aeropuertos, vuelo.Dst))

      // Sumar al acumulador la diferencia de horas
      acc + math.abs((HLvGMT * 60 + vuelo.ML) - (HSvGMT * 60 + vuelo.MS))
    })
  }

  def obtenerTiempoEspera(aeropuertos: List[Aeropuerto], itinerario: Itinerario, acc: Int): Int = {
    itinerario match {
      case Nil          => acc
      case vuelo :: Nil => acc
      case vuelo1 :: vuelo2 :: tail => {
        // Del vuelo1 obtener la hora de llegada en GMT (Se supone que el GMT del vuelo2 es el msimo para el Org)
        val HLv1GMT =
          if (vuelo1.HL - obtenerGMT(aeropuertos, vuelo1.Dst) < 0)
            (vuelo1.HL - obtenerGMT(aeropuertos, vuelo1.Dst) + 24)
          else (vuelo1.HL - obtenerGMT(aeropuertos, vuelo1.Dst))
        // Del vuelo2 obtener la hora de salida en GMT
        val HSv2GMT =
          if (vuelo2.HS - obtenerGMT(aeropuertos, vuelo2.Org) < 0)
            (vuelo2.HS - obtenerGMT(aeropuertos, vuelo2.Org) + 24)
          else (vuelo2.HS - obtenerGMT(aeropuertos, vuelo2.Org))

        obtenerTiempoEspera(aeropuertos,
          vuelo2 :: tail,
          acc + math.abs((HSv2GMT * 60 + vuelo2.MS) - (HLv1GMT * 60 + vuelo1.MS))
        )
      }
    }
  }

  def itinerarios(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, dos códigos de aeropuerto
    // y devuelve todos los itinerarios entre esos dos aeropuertos

    // Se deben considerar todos los itinerarios posibles, sin importar
    // Es decir, la ruta más corta, la ruta con menos escalas, la ruta con menos tiempo en el aire, etc.

    (a: String, b: String) =>
      {
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

  def itinerariosTiempo(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve una función que devuelve los tres (si los hay) itinerarios que minimizan el
    // tiempo de viaje entre esos dos aeropuertos

    // Los tiempos están en hora local del aeropuerto de origen
    // Entonces hay que convetirlos a horas en GMT
    (a: String, b: String) =>
      {

        // Función que obtiene el tiempo total de un itinerario
        def obtenerTiempoTotal(itinerario: Itinerario): Int = {
          obtenerTiempoVuelo(aeropuertos, itinerario) + obtenerTiempoEspera(
            aeropuertos,
            itinerario,
            0
          )
        }

        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) => obtenerTiempoTotal(a) < obtenerTiempoTotal(b))
          .take(3)
      }
  }

  def itinerariosEscalas(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan el numero
    // de cambios de avión entre esos dos aeropuertos

    (a: String, b: String) =>
      {

        def calcularEscalasTotales(itinerario: Itinerario): Int = {
          itinerario.map(_.Esc).sum + (itinerario.length - 1)
        }

        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) =>
            calcularEscalasTotales(a) < calcularEscalasTotales(b)
          )
          .take(3)
      }
  }

  def itinerariosAire(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan
    // el tiempo en el aire entre esos dos aeropuertos

    (a: String, b: String) =>
      {
        itinerarios(vuelos, aeropuertos)(a, b)
          .sortWith((a, b) =>
            obtenerTiempoVuelo(aeropuertos, a) < obtenerTiempoVuelo(
              aeropuertos,
              b
            )
          )
          .take(3)
      }
  }

  def itinerarioSalida(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String, Int, Int) => Itinerario = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto, y h:m una hora de la cita en c2
    // y devuelve el itinerario que optimiza la hora de salida para llegar a tiempo a la cita

    (a: String, b: String, h: Int, m: Int) =>
      {

        println(itinerarios(vuelos, aeropuertos)(a, b))

        // Calcular todos los tiempos de vuelo de los itinerarios que van de a a b
        println(itinerarios(vuelos, aeropuertos)(a, b)
          .map(x => {
            println(s"Tiempo de vuelo: ${obtenerTiempoVuelo(aeropuertos, x)}")
            println(s"Tiempo de espera: ${obtenerTiempoEspera(aeropuertos, x, 0)}")
            obtenerTiempoEspera(aeropuertos, x, 0) + obtenerTiempoVuelo(aeropuertos, x)
          }))


          // Itinerario vacio
          List()

      }
  }
}
