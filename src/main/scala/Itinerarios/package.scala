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

  def itinerariosEscalas(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]) : (String,String) => List[Itinerario] = {
    // Calcula el numero total de escalas en un itinerario, para cada uno se suman las escalas y se añaden el
    // numero de cambios de avión, que es igual al tamaño de los itinerarios menos 1
    def contarEscalas(itinerario: Itinerario): Int = {
      itinerario.map(_.Esc).sum + itinerario.size
    }

    //Itinerarios, metodo de busqueda
    def itinerarios(codigo1: String, codigo2: String): List[Itinerario] = {
      def vuelosDesde(codigo: String): List[Vuelo] =
        vuelos.filter(v => v.Org == codigo)

      def itinerariosDesdeVuelo(vuelo: Vuelo, destino: String): List[Itinerario] =
        if (vuelo.Dst == destino) List(List(vuelo))
        else vuelosDesde(vuelo.Dst).flatMap(v => itinerariosDesdeVuelo(v, destino).map(vuelo :: _))

      vuelosDesde(codigo1).flatMap(v => itinerariosDesdeVuelo(v, codigo2)).take(3)
    }

    // Retornar los itinerarios ordenados en base al numero de escalas calculado
    (codigo1: String, codigo2: String) => itinerarios(codigo1, codigo2).sortBy(contarEscalas)
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
