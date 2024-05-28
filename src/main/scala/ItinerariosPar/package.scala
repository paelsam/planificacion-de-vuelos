import common._
import scala.collection.parallel.CollectionConverters._

import Datos.Aeropuerto
import Datos.Itinerario
import Datos.Vuelo
import Itinerarios.obtenerGMT

package object ItinerariosPar {

  def obtenerTiempoEsperaPar( aeropuertos: List[Aeropuerto], itinerario: Itinerario,acc: Int ): Int = {
    itinerario match {
      case Nil | _ :: Nil => acc
      case vuelo1 :: vuelo2 :: tail => {
        val (v1DstGMT, v2OrgGMT) = parallel(obtenerGMT(aeropuertos, vuelo1.Dst), obtenerGMT(aeropuertos, vuelo2.Org))

        val HLv1GMT = if (vuelo1.HL - v1DstGMT < 0) (vuelo1.HL - v1DstGMT + 24) else (vuelo1.HL - v1DstGMT)
        val HSv2GMT = if (vuelo2.HS - v2OrgGMT < 0) (vuelo2.HS - v2OrgGMT + 24) else (vuelo2.HS - v2OrgGMT)

        val diferenciaHvGMT = (HSv2GMT * 60 + vuelo2.MS) - (HLv1GMT * 60 + vuelo1.ML)
        
        obtenerTiempoEsperaPar(aeropuertos, vuelo2 :: tail, acc + diferenciaHvGMT)
      }
    }
  }

  def obtenerTiempoVueloPar( aeropuertos: List[Aeropuerto], itinerario: Itinerario ): Int = {    
    itinerario.foldRight(0)((vuelo, acc) => {
      val (vOrgGMT, vDstGMT) = parallel(obtenerGMT(aeropuertos, vuelo.Org), obtenerGMT(aeropuertos, vuelo.Dst))
      val HSvGMT = if (vuelo.HS - vOrgGMT < 0) (vuelo.HS - vOrgGMT + 24) else (vuelo.HS - vOrgGMT)
      val HLvGMT = if (vuelo.HL - vDstGMT < 0) (vuelo.HL - vDstGMT + 24) else (vuelo.HL - vDstGMT)
      val diferenciaHvGMT = ((HLvGMT * 60 + vuelo.ML) - (HSvGMT * 60 + vuelo.MS))
      if ( diferenciaHvGMT < 0 ) acc +  diferenciaHvGMT + (24 * 60) else acc + diferenciaHvGMT
    })
  }

  def itinerariosPar( vuelos: List[Vuelo],aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {

      val vuelosPorOrigen = vuelos.filter(_.Org == a)

      def aeropuetoVisitado( cod: String, vuelosVisitados: Set[Vuelo] ): Boolean = {
        vuelosVisitados.exists(vuelo => vuelo.Org == cod || vuelo.Dst == cod)
      }

      def buscarItinerarios(cod1: String, cod2: String, vuelosVisitados: Set[Vuelo]): List[Itinerario] = {
        if (cod1 == cod2) List(List())
        else {
          vuelosPorOrigen
          .filter(vuelo => !vuelosVisitados.contains(vuelo) && !aeropuetoVisitado(vuelo.Dst, vuelosVisitados))
          .map( vuelo => 
            task(buscarItinerarios(vuelo.Dst, cod2, vuelosVisitados + vuelo).map(itinerario => vuelo :: itinerario)) 
          ).flatMap(_.join())      
        }  
      }
      buscarItinerarios(a, b, Set())
    }
  }

  def itinerariosTiempoPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {

      // Función que obtiene el tiempo total de un itinerario
      def obtenerTiempoTotal(itinerario: Itinerario): Int = {
        val (tiempoVuelo, tiempoEspera) = parallel(obtenerTiempoVueloPar(aeropuertos, itinerario), obtenerTiempoEsperaPar(aeropuertos, itinerario, 0))
        tiempoVuelo + tiempoEspera
      }

      // Obtener todos los itinerarios entre los aeropuertos a y b
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(a, b)

      // Mapear cada itinerario a su tiempo total en paralelo
      todosItinerarios.map(itinerario => task((itinerario, obtenerTiempoTotal(itinerario))))
                      .map(_.join()).sortBy(_._2).take(3).map(_._1)
    }
  }

  def itinerariosEscalasPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {

      // Obtener todos los itinerarios entre los aeropuertos a y b
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(a, b)

      def calcularEscalasTotales(itinerario: Itinerario): Int = {
        itinerario.map(_.Esc).sum + (itinerario.length - 1)
      }

      // Mapear cada itinerario a su número total de escalas en paralelo
      todosItinerarios.map(itinerario => task((itinerario, calcularEscalasTotales(itinerario))))
                      .map(_.join()).sortBy(_._2).take(3).map(_._1)

    }
  }


  def itinerariosAirePar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(a, b)

      todosItinerarios.map { itinerario => task((itinerario, obtenerTiempoVueloPar(aeropuertos, itinerario))) }
                      .map(_.join()).sortBy(_._2).take(3).map(_._1)
    }
  }

  def itinerarioSalidaPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => Itinerario = {
    (cod1: String, cod2: String, h: Int, m: Int) => {

      //Obtener todos los itinerarios entre los aeropuertos cod1 y cod2 para convertir en una lista paralela para el filtro
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2).par

      val posiblesResultados = todosItinerarios.filter(itinerario => {
          val vDstGMT = obtenerGMT(aeropuertos, itinerario.last.Dst)
          (itinerario.last.HL - vDstGMT) * 60 + itinerario.last.ML <= ((h - vDstGMT) * 60 + m)
      }).par

      // Encontrar el itinerario con la hora de salida más temprana en paralelo
      if (posiblesResultados.nonEmpty) posiblesResultados.minBy(itinerario => itinerario.maxBy(_.HS).HS) else List()
      
    }
  }

}
