import Datos._
import ItinerariosPar._
import Itinerarios._
import Benchmark.compararAlgoritmos
import Benchmark.compararAlgoritmosSalida

@main def main(): Unit = {

  val vuelos60 = vuelosA1 ++ vuelosA2 ++ vuelosA3 ++ vuelosA4
  val vuelos100 = vuelosB1 ++ vuelos60
  val vuelos160 = vuelosC1 ++ vuelos60
  val vuelos200 = vuelosC1 ++ vuelosC2
  val vuelos260 = vuelos200 ++ vuelos60
  val vuelos275 = vuelos260 ++ vuelosA1
  val vuelos300 = vuelosC1 ++ vuelosC2 ++ vuelosC3

  // for (i <- 1 to 3) {
  //   println(s"Iteración #$i:")

  //   println("Prueba con una lista de 60 vuelos")
  //   println(
  //     compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(
  //       vuelos60,
  //       aeropuertos
  //     )("HOU", "BNA", 18, 30)
  //   )

  //   println("Prueba con una lista de 100 vuelos")
  //   println(
  //     compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(
  //       vuelos100,
  //       aeropuertos
  //     )("DFW", "ORD", 18, 30)
  //   )

  //   println("Prueba con una lista de 160 vuelos")
  //   println(
  //     compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(
  //       vuelos160,
  //       aeropuertos
  //     )("ORD", "TPA", 18, 30)
  //   )

  //   println("Prueba con una lista de 200 vuelos")
  //   println(
  //     compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(
  //       vuelos200,
  //       aeropuertos
  //     )("ORD", "TPA", 18, 30)
  //   )
  // }

  println("\nPruebas para funciones de itinerarios:")
  val todosLosVuelos = vuelosC5 ++ vuelosC1

  println(itinerarios(todosLosVuelos, aeropuertos)("ATL", "MIA").length)
  println(itinerarios(todosLosVuelos, aeropuertos)("LAX", "BOS").length)
  println(itinerarios(todosLosVuelos, aeropuertos)("LAX", "JFK").length)
  println(itinerarios(todosLosVuelos, aeropuertos)("LAX", "XYZ").length)

  println(itinerariosTiempo(todosLosVuelos, aeropuertos)("LAX", "JFK").length)
  println(itinerariosEscalas(todosLosVuelos, aeropuertos)("LAX", "JFK").length)
  println(itinerariosAire(todosLosVuelos, aeropuertos)("LAX", "JFK").length)

  // itinearioSalida

  val itsl1 = itinerarioSalida(todosLosVuelos, aeropuertos)("ATL", "LAX", 11, 0)
  val itsl2 = itinerarioSalida(todosLosVuelos, aeropuertos)("JFK", "LAX", 22, 0)
  val itsl3 = itinerarioSalida(todosLosVuelos, aeropuertos)("ORD", "MIA", 23, 59)
  val itsl4 = itinerarioSalida(todosLosVuelos, aeropuertos)("ATL", "ORD", 9, 0)
  val itsl5 = itinerarioSalida(todosLosVuelos, aeropuertos)("BOS", "LAX", 12, 0)

  println(itsl1)
  println(itsl2)
  println(itsl3)
  println(itsl4)
  println(itsl5)

}
