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
    itinerario.foldRight(0)((vuelo, acc) => {
      val (vOrgGMT, vDstGMT) = (obtenerGMT(aeropuertos, vuelo.Org), obtenerGMT(aeropuertos, vuelo.Dst))
      val HSvGMT = if (vuelo.HS - vOrgGMT < 0) (vuelo.HS - vOrgGMT + 24) else (vuelo.HS - vOrgGMT)
      val HLvGMT = if (vuelo.HL - vDstGMT < 0) (vuelo.HL - vDstGMT + 24) else (vuelo.HL - vDstGMT)
      val diferenciaHvGMT = ((HLvGMT * 60 + vuelo.ML) - (HSvGMT * 60 + vuelo.MS))
      if ( diferenciaHvGMT < 0 ) acc +  diferenciaHvGMT + (24 * 60) else acc + diferenciaHvGMT
    })
  }


  def obtenerTiempoEspera( aeropuertos: List[Aeropuerto], itinerario: Itinerario,acc: Int ): Int = {
    itinerario match {
      case Nil          => acc
      case vuelo :: Nil => acc
      case vuelo1 :: vuelo2 :: tail => {
        val (v1DstGMT, v2OrgGMT) = (obtenerGMT(aeropuertos, vuelo1.Dst), obtenerGMT(aeropuertos, vuelo2.Org))

        val HLv1GMT = if (vuelo1.HL - v1DstGMT  < 0) (vuelo1.HL - v1DstGMT + 24) else (vuelo1.HL - v1DstGMT)
        val HSv2GMT = if (vuelo2.HS - v2OrgGMT < 0) (vuelo2.HS - v2OrgGMT + 24) else (vuelo2.HS - v2OrgGMT)

        val diferenciaHvGMT = (HSv2GMT * 60 + vuelo2.MS) - (HLv1GMT * 60 + vuelo1.ML)
        obtenerTiempoEspera( aeropuertos, vuelo2 :: tail, acc + diferenciaHvGMT)
      }
    }
  }

  def itinerarios( vuelos: List[Vuelo],aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {
      def aeropuetoVisitado( cod: String, vuelosVisitados: Set[Vuelo] ): Boolean = {
        vuelosVisitados.filter(vuelo => vuelo.Org == cod || vuelo.Dst == cod).size >= 1
      }

      def buscarItinerarios(cod1: String, cod2: String, vuelosVisitados: Set[Vuelo]): List[Itinerario] = {
        if (cod1 == cod2) List(List())
        else {
          vuelos.filter(vuelo => vuelo.Org == cod1 && !vuelosVisitados.contains(vuelo) && !aeropuetoVisitado(vuelo.Dst, vuelosVisitados))
            .flatMap( vuelo =>
              buscarItinerarios(vuelo.Dst, cod2, vuelosVisitados + vuelo).map(itinerario => vuelo :: itinerario)
            )
        }
      }
      buscarItinerarios(a, b, Set())
    }
  }


  def itinerariosTiempo( vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
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
    (a: String, b: String) => {
      itinerarios(vuelos, aeropuertos)(a, b)
        .sortWith((a, b) => obtenerTiempoVuelo(aeropuertos, a) < obtenerTiempoVuelo(aeropuertos,b))
        .take(3)
    }
  }

  def itinerariosSalida(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto] ): (String, String, Int, Int) => Itinerario = {
    (cod1: String, cod2: String, h: Int, m: Int) => {
      val posiblesResultados = for {
        itinerario <- itinerarios(vuelos, aeropuertos)(cod1, cod2)
        if ((itinerario.last.HL - obtenerGMT(aeropuertos,itinerario.last.Dst)) * 60 + itinerario.last.ML <= ((h - obtenerGMT(aeropuertos, itinerario.last.Dst)) * 60 + m))
      } yield itinerario

      if (!posiblesResultados.isEmpty) posiblesResultados.minBy(itinerario => itinerario.maxBy(_.HS).HS) else List()
    }
  }
}
