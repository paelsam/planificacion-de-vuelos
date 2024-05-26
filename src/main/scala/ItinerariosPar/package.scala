import common.*

import scala.collection.parallel.CollectionConverters.*
import Datos.Aeropuerto
import Datos.Itinerario
import Datos.Vuelo
import Itinerarios.{itinerarios, obtenerGMT, obtenerTiempoEspera, obtenerTiempoVuelo}

package object ItinerariosPar {

  def obtenerTiempoEsperaPar( aeropuertos: List[Aeropuerto], itinerario: Itinerario,acc: Int ): Int = {
    itinerario match {
      case Nil          => acc
      case vuelo :: Nil => acc
      case vuelo1 :: vuelo2 :: tail => {
        val (v1DstGMT, v2OrgGMT) = (obtenerGMT(aeropuertos, vuelo1.Dst), obtenerGMT(aeropuertos, vuelo2.Org))

        val HLv1GMT = if (vuelo1.HL - v1DstGMT < 0) (vuelo1.HL - v1DstGMT + 24) else (vuelo1.HL - v1DstGMT)
        val HSv2GMT = if (vuelo2.HS - v2OrgGMT < 0) (vuelo2.HS - v2OrgGMT + 24) else (vuelo2.HS - v2OrgGMT)

        val diferenciaHvGMT = (HSv2GMT * 60 + vuelo2.MS) - (HLv1GMT * 60 + vuelo1.ML)
        val tiemposEsperaTask = task {
          obtenerTiempoEspera(aeropuertos, vuelo2 :: tail, acc + diferenciaHvGMT)
        }
        tiemposEsperaTask.join()
      }
    }
  }

  def itinerariosPar( vuelos: List[Vuelo],aeropuertos: List[Aeropuerto] ): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {
      def aeropuetoVisitado( cod: String, vuelosVisitados: Set[Vuelo] ): Boolean = {
        vuelosVisitados.filter(vuelo => vuelo.Org == cod || vuelo.Dst == cod).size >= 1
      }

      def buscarItinerarios(cod1: String, cod2: String, vuelosVisitados: Set[Vuelo]): List[Itinerario] = {
        if (cod1 == cod2) List(List())
        else {
          val vuelosFiltrados = vuelos.filter(vuelo => vuelo.Org == cod1 && !vuelosVisitados.contains(vuelo) && !aeropuetoVisitado(vuelo.Dst, vuelosVisitados))
          val itinerarios = vuelosFiltrados.map { vuelo =>
            task {
              //Ejecuta cada rama de busqueda en paralelo
              buscarItinerarios(vuelo.Dst, cod2, vuelosVisitados + vuelo).map(itinerario => vuelo :: itinerario)
            }
          }
          itinerarios.flatMap(_.join())
        }
      }
      buscarItinerarios(a, b, Set())
    }
  }

  def itinerariosTiempoPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {

      // Función que obtiene el tiempo total de un itinerario
      def obtenerTiempoTotal(itinerario: Itinerario): Int = {
        obtenerTiempoVuelo(aeropuertos, itinerario) + obtenerTiempoEsperaPar(aeropuertos, itinerario, 0)
      }

      // Obtener todos los itinerarios entre los aeropuertos a y b
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(a, b)

      // Mapear cada itinerario a su tiempo total en paralelo
      val itinerariosConTiempo = todosItinerarios.map { itinerario =>
        task {
          (itinerario, obtenerTiempoTotal(itinerario))
        }
      }

      // Unir los resultados
      val itinerariosTiempoResultados = itinerariosConTiempo.map(_.join())

      // Ordenar los itinerarios por tiempo total y tomar los tres primeros
      itinerariosTiempoResultados.sortBy(_._2).take(3).map(_._1)
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
      val itinerariosConEscalas = todosItinerarios.map { itinerario =>
        task {
          (itinerario, calcularEscalasTotales(itinerario))
        }
      }

      // Unir los resultados
      val itinerariosConEscalasResultados = itinerariosConEscalas.map(_.join())

      // Ordenar los itinerarios por el número total de escalas y tomar los tres primeros 
      itinerariosConEscalasResultados.sortBy(_._2).take(3).map(_._1)

    }
  }

  def itinerariosAirePar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    (a: String, b: String) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(a, b)

      val itinerariosConTiempoVuelo = todosItinerarios.map { itinerario =>
        task {
          (itinerario, obtenerTiempoVuelo(aeropuertos, itinerario))
        }
      }

      // Unir los resultados
      val itinerariosConTiempoVueloResultados = itinerariosConTiempoVuelo.map(_.join())

      //ordena la lista de tuplas en base al segundo elemento, toma los 3 mejores y finalmente extrae solos los itinerarios
      itinerariosConTiempoVueloResultados.sortBy(_._2).take(3).map(_._1)
    }
  }


  def itinerariosSalidaPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => Itinerario = {
    (cod1: String, cod2: String, h: Int, m: Int) => {

      //Obtener todos los itinerarios entre los aeropuertos cod1 y cod2 para convertir en una lista paralela para el filtro
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2).par

      // Filtrar los itinerarios que llegan antes de la hora especificada en paralelo
      val posiblesResultados = todosItinerarios.filter { itinerario =>
        val tiempoLlegadaGMT = (itinerario.last.HL - obtenerGMT(aeropuertos, itinerario.last.Dst)) * 60 + itinerario.last.ML
        val tiempoEspecificadoGMT = (h - obtenerGMT(aeropuertos, itinerario.last.Dst)) * 60 + m
        tiempoLlegadaGMT <= tiempoEspecificadoGMT
      }.toList

      // Encontrar el itinerario con la hora de salida más temprana en paralelo
      if (posiblesResultados.nonEmpty) {
        val minItinerario = task {
          posiblesResultados.minBy(itinerario => itinerario.maxBy(_.HS).HS)
        }
        minItinerario.join()
      } else {
        List()
      }
    }

  }
}
