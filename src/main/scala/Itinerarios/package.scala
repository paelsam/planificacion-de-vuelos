import Datos.Vuelo
import Datos.Aeropuerto
import Datos.Itinerario

package object Itinerarios {

  def itinerarios(
      vuelos: List[Vuelo],
      aeropuertos: List[Aeropuerto]
  ): (String, String) => List[Itinerario] = {
    // Recibe vuelos, una lista de todos los vuelos disponibles y
    // aeropuertos, una lista de todos los aeropuertos
    // y devuelve una función que recibe c1 y c2, dos códigos de aeropuerto
    // y devuelve todos los itinerarios entre esos dos aeropuertos

    (a: String, b: String) => List()
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

    (a: String, b: String) => List()
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

    (a: String, b: String) => List()
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

    (a: String, b: String) => List()
  }

  def itinerarioSalida(
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
