import Itinerarios._
import ItinerariosPar._
import Datos._

@main def main() = {

  val itsTiempoCurso = itinerariosTiempo(vuelosCurso, aeropuertosCurso)

// prueba itinerariosTiempo
  val itst1 = itsTiempoCurso("MID", "SVCS")
  val itst2 = itsTiempoCurso("CLO", "SVCS")

// 4 itinerarios CLO-SVO

  val itst3 = itsTiempoCurso("CLO", "SVO")

//2 itinerarios CLO-MEX

  val itst4 = itsTiempoCurso("CLO", "MEX")

//2 itinerarios CTG-PTY
  val itst5 = itsTiempoCurso("CTG", "PTY")

// prueba itinerariosEscalas
  val itsEscalasCurso = itinerariosEscalas(vuelosCurso, aeropuertosCurso)

  val itsc1 = itsEscalasCurso("MID", "SVCS")
  val itsc2 = itsEscalasCurso("CLO", "SVCS")

// 4 itinerarios CLO-SVO

  val itsc3 = itsEscalasCurso("CLO", "SVO")

//2 itinerarios CLO-MEX

  val itsc4 = itsEscalasCurso("CLO", "MEX")

//2 itinerarios CTG-PTY
  val itsc5 = itsEscalasCurso("CTG", "PTY")

// prueba itinerariosAire
  val itsAireCurso = itinerariosAire(vuelosCurso, aeropuertosCurso)

  val itsa1 = itsAireCurso("MID", "SVCS")
  val itsa2 = itsAireCurso("CLO", "SVCS")

// 4 itinerarios CLO-SVO

  val itsa3 = itsAireCurso("CLO", "SVO")

//2 itinerarios CLO-MEX

  val itsa4 = itsAireCurso("CLO", "MEX")

//2 itinerarios CTG-PTY
  val itsa5 = itsAireCurso("CTG", "PTY")

  // Imprimir vals

  println("Prueba itinerariosTiempo")
  println("\n")
  println(itst1)
  println("\n")
  println(itst2)
  println("\n")
  println(itst3)
  println("\n")
  println(itst4)
  println("\n")
  println(itst5)
  println("\n")

  println("Prueba itinerariosEscalas")
  println("\n")
  println(itsc1)
  println("\n")
  println(itsc2)
  println("\n")
  println(itsc3)
  println("\n")
  println(itsc4)
  println("\n")
  println(itsc5)
  println("\n")

  println("Prueba itinerariosAire")
  println("\n")
  println(itsa1)
  println("\n")
  println(itsa2)
  println("\n")
  println(itsa3)
  println("\n")
  println(itsa4)
  println("\n")
  println(itsa5)
  println("\n")

  println("Prueba itinerarioSalida")
  val itSalidaCurso = itinerarioSalida(vuelosCurso, aeropuertosCurso)

  val itsal1 = itSalidaCurso("CTG", "PTY", 11, 40)
  val itsal2 = itSalidaCurso("CTG", "PTY", 11, 55)
  val itsal3 = itSalidaCurso("CTG", "PTY", 10, 30)

  println(itsal1)
  println("\n")
  println(itsal2)
  println("\n")
  println(itsal3)
  println("\n")

}
