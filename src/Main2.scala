import Datos._
import ItinerariosPar._
import Itinerarios._
import Benchmark.compararAlgoritmos
import Benchmark.compararAlgoritmosSalida

@main def main(): Unit = {

  // Pruebas para itinerarios
  println("\nPruebas para itinerarios:")
  val itinerariosFun = itinerarios(vuelosCurso, aeropuertosCurso)
  println(itinerariosFun("BOG", "MAD"))
  println(itinerariosFun("CLO", "SVO"))
  println(itinerariosFun("MID", "HND"))
  println(itinerariosFun("BOG", "BOG"))

  // Pruebas para itinerariosTiempo
  println("\nPruebas para itinerariosTiempo:")
  val itinerariosTiempoFun = itinerariosTiempo(vuelosCurso, aeropuertosCurso)
  println(itinerariosTiempoFun("CLO", "SVO"))
  println(itinerariosTiempoFun("MID", "SVCS"))
  println(itinerariosTiempoFun("MID", "HND"))

  // Pruebas para itinerariosEscalas
  println("\nPruebas para itinerariosEscalas:")
  val itinerariosEscalasFun = itinerariosEscalas(vuelosCurso, aeropuertosCurso)
  println(itinerariosEscalasFun("CLO", "SVO"))
  println(itinerariosEscalasFun("BOG", "MAD"))
  println(itinerariosEscalasFun("MID", "HND"))

  // Pruebas para itinerariosAire
  println("\nPruebas para itinerariosAire:")
  val itinerariosAireFun = itinerariosAire(vuelosCurso, aeropuertosCurso)
  println(itinerariosAireFun("CLO", "SVO"))
  println(itinerariosAireFun("BOG", "MAD"))
  println(itinerariosAireFun("MID", "HND"))

  // Pruebas para itinerarioSalida
  println("\nPruebas para itinerarioSalida:")
  val itinerarioSalidaFun = itinerarioSalida(vuelosCurso, aeropuertosCurso)
  println(itinerarioSalidaFun("BOG", "MAD", 13, 0))
  println(itinerarioSalidaFun("CLO", "MAD", 14, 0))
  println(itinerarioSalidaFun("BOG", "MAD", 11, 0))
  println(itinerarioSalidaFun("MID", "SVCS", 1, 0))

  // prueba itinerarioSalida
  val itSalidaCurso = itinerarioSalida(vuelosCurso, aeropuertosCurso)

  val itsal1 = itSalidaCurso("CTG", "PTY", 11, 40)
  val itsal2 = itSalidaCurso("CTG", "PTY", 11, 55)
  val itsal3 = itSalidaCurso("CTG", "PTY", 10, 30)

  println(itsal1)
  println(itsal2)
  println(itsal3)
}
