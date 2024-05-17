import Itinerarios._
import ItinerariosPar._
import Datos._

@main def main() = {
  // prueba itinerarioSalida
  val itSalidaCurso = itinerarioSalida(vuelosCurso, aeropuertosCurso)

  val itsal1 = itSalidaCurso("CTG", "PTY", 11, 40)
  val itsal2 = itSalidaCurso("CTG", "PTY", 11, 55)
  val itsal3 = itSalidaCurso("CTG", "PTY", 10, 30)


  println(itsal1)
  println(itsal2)
  println(itsal3)
}
