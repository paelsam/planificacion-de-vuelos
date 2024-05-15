import common._
import scala.collection.parallel.CollectionConverters._

import Datos.Aeropuerto
import Datos.Itinerario
import Datos.Vuelo

package object ItinerariosPar {

  def itinerariosPar(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, dos códigos de aeropuerto
    // y devuelve todos los itinerarios entre esos dos aeropuertos

    (a: String, b: String) => List()
  }

  def itinerariosTiempoPar(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve una función que devuelve los tres (si los hay) itinerarios que minimizan el
    // tiempo de viaje entre esos dos aeropuertos

    (a: String, b: String) => List()
  }

  def itinerariosEscalasPar(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan el numero
    // de cambios de avión entre esos dos aeropuertos

    (a: String, b: String) => List()
  }

  def itinerariosAirePar(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto
    // y devuelve los tres (si los hay) itinerarios que minimizan
    // el tiempo en el aire entre esos dos aeropuertos

    (a: String, b: String) => List()
  }

  def itinerarioSalidaPar(
      vuelos: List[Vuelo],
      aeropuerto: List[Aeropuerto]
  ): (String, String, Int, Int) => Itinerario = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, códigos de aeropuerto, y h:m una hora de la cita en c2
    // y devuelve el itinerario que optimiza la hora de salida para llegar a tiempo a la cita

    (a: String, b: String, h: Int, m: Int) => List()
  }

}
