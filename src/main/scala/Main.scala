import Datos._
import ItinerariosPar._
import Itinerarios._
import Benchmark.compararAlgoritmos
import Benchmark.compararAlgoritmosSalida

@main
def main() = {

  /*
    val random = new scala.util.Random()

    // Obtener dos codigo de aeropuertos de forma aleatoria
    val codigoAeropuerto1 = random.nextInt(aeropuertos.size)
    val codigoAeropuerto2 = random.nextInt(aeropuertos.size)

    val (cod1, cod2) = if (codigoAeropuerto1 == codigoAeropuerto2) (aeropuertos(random.nextInt(aeropuertos.size)), aeropuertos(random.nextInt(aeropuertos.size))) else (aeropuertos(codigoAeropuerto1), aeropuertos(codigoAeropuerto2))
   */

  // Sección de pruebas
  // Primero, definir las lista de vuelos por tamaño que se usarán en las pruebas
  // Se usarán lista de 60 a 300 vuelos de cada tipo

  val vuelos60 = vuelosA1 ++ vuelosA2 ++ vuelosA3 ++ vuelosA4
  val vuelos100 = vuelosB1 ++ vuelos60
  val vuelos160 = vuelosC1 ++ vuelos60
  val vuelos200 = vuelosC1 ++ vuelosC2
  val vuelos260 = vuelos200 ++ vuelos60
  val vuelos275 = vuelos260 ++ vuelosA1
  val vuelos300 = vuelosC1 ++ vuelosC2 ++ vuelosC3

  for (i <- 1 to 3) {
    println(s"Iteración #$i:")

    println("Prueba con una lista de 60 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos60, aeropuertos)("HOU","BNA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos60, aeropuertos)("HOU","BNA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos60, aeropuertos)("HOU","BNA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos60, aeropuertos)("HOU","BNA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos60, aeropuertos)("HOU","BNA", 18, 30))

    println("Prueba con una lista de 100 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos100, aeropuertos)("DFW","ORD"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos100, aeropuertos)("DFW","ORD"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos100, aeropuertos)("DFW","ORD"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos100, aeropuertos)("DFW","ORD"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos100, aeropuertos)("DFW","ORD", 18, 30))

    println("Prueba con una lista de 160 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos160, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos160, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos160, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos160, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos160, aeropuertos)("ORD","TPA", 18, 30))

    println("Prueba con una lista de 200 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos200, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos200, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos200, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos200, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos200, aeropuertos)("ORD","TPA", 18, 30))

    println("Prueba con una lista de 260 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos260, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos260, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos260, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos260, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos260, aeropuertos)("ORD","TPA", 18, 30))

    println("Prueba con una lista de 275 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos275, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos275, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos275, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos275, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos275, aeropuertos)("ORD","TPA", 18, 30))

    println("Prueba con una lista de 300 vuelos")
    println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos300, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos300, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos300, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos300, aeropuertos)("ORD","TPA"))
    println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos300, aeropuertos)("ORD","TPA", 18, 30))
  }
}
