import Datos._
import ItinerariosPar._
import Itinerarios._
import Benchmark.compararAlgoritmos
import Benchmark.compararAlgoritmosSalida

@main
def main() = {

  val random = new scala.util.Random()

  // Obtener dos codigo de aeropuertos de forma aleatoria
  val codigoAeropuerto1 = random.nextInt(aeropuertos.size)
  val codigoAeropuerto2 = random.nextInt(aeropuertos.size)

  val (cod1, cod2) = (aeropuertos(codigoAeropuerto1), aeropuertos(codigoAeropuerto2))

  // Sección de pruebas
  // Primero, definir las lista de vuelos por tamaño que se usarán en las pruebas
  val vuelos75 = vuelosA1 ++ vuelosA2 ++ vuelosA3 ++ vuelosA4 ++ vuelosA5 // Hacer pruebas con cada lista de vuelosA
  val vuelos200 = vuelosB1 ++ vuelosB2 ++ vuelosB3 ++ vuelosB4 ++ vuelosB5 // Hacer pruebas con cada lista de vuelosB
  val vuelos300 = vuelosC1 ++ vuelosC2 ++ vuelosC3 
  val vuelos400 = vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4
  val vuelos500 = vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5 // Hacer pruebas con cada lista de vuelosC
  val vuelos1500 = vuelosD1 ++ vuelosD2 ++ vuelosD3 // Hacer pruebas con cada lista de vuelosD

  println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos75, aeropuertos)("HOU", "BNA"))
  println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos200, aeropuertos)("DFW", "ORD"))
  println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos300, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos400, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelos500, aeropuertos)("ORD", "TPA"))

  println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos75, aeropuertos)("HOU", "BNA"))
  println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos200, aeropuertos)("DFW", "ORD"))
  println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos300, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos400, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos500, aeropuertos)("ORD", "TPA"))

  println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos75, aeropuertos)("HOU", "BNA"))
  println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos200, aeropuertos)("DFW", "ORD"))
  println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos300, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos400, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos500, aeropuertos)("ORD", "TPA"))

  println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos75, aeropuertos)("HOU", "BNA"))
  println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos200, aeropuertos)("DFW", "ORD"))
  println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos300, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos400, aeropuertos)("ORD", "TPA"))
  println(compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos500, aeropuertos)("ORD", "TPA"))

  println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos75, aeropuertos)("HOU", "BNA", 18, 30))
  println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos200, aeropuertos)("DFW", "ORD", 18, 30))
  println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos300, aeropuertos)("ORD", "TPA", 18, 30))
  println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos400, aeropuertos)("ORD", "TPA", 18, 30))
  println(compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos500, aeropuertos)("ORD", "TPA", 18, 30))

//   // Ejemplo curso pequeño
//   val itsCursoPar = itinerariosPar(vuelosCurso, aeropuertosCurso)
// //2.1 Aeropuertos incomunicados
//   val its1 = itsCursoPar("MID", "SVCS")
//   val its2 = itsCursoPar("CLO", "SVCS")

// // 4 itinerarios CLO-SVO

//   val its3 = itsCursoPar("CLO", "SVO")

// //2 itinerarios CLO-MEX

//   val its4 = itsCursoPar("CLO", "MEX")

// //2 itinerarios CTG-PTY
//   val its5 = itsCursoPar("CTG", "PTY")

//   println("\n")
//   println(its1)
//   println("\n")
//   println(its2)
//   println("\n")
//   println(its3)
//   println("\n")
//   println(its4)
//   println("\n")
//   println(its5)
//   println("\n")

//   val itsTiempoPar = itinerariosTiempoPar(vuelosCurso, aeropuertosCurso)

//   val itst1 = itsTiempoPar("MID", "SVCS")
//   val itst2 = itsTiempoPar("CLO", "SVCS")

//   val itst3 = itsTiempoPar("CLO", "SVO")

//   val itst4 = itsTiempoPar("CLO", "MEX")

//   val itst5 = itsTiempoPar("CTG", "PTY")

//   println("\n")
//   println(itst1)
//   println("\n")
//   println(itst2)
//   println("\n")
//   println(itst3)
//   println("\n")
//   println(itst4)
//   println("\n")
//   println(itst5)
//   println("\n")

//   val itsEscalasPar = itinerariosEscalasPar(vuelosCurso, aeropuertosCurso)

//   val itse1 = itsEscalasPar("MID", "SVCS")
//   val itse2 = itsEscalasPar("CLO", "SVCS")

//   val itse3 = itsEscalasPar("CLO", "SVO")

//   val itse4 = itsEscalasPar("CLO", "MEX")

//   val itse5 = itsEscalasPar("CTG", "PTY")

//   println("\n")
//   println(itse1)
//   println("\n")
//   println(itse2)
//   println("\n")
//   println(itse3)
//   println("\n")
//   println(itse4)
//   println("\n")
//   println(itse5)
//   println("\n")

//   val itsAirePar = itinerariosAirePar(vuelosCurso, aeropuertosCurso)

//   val itsa1 = itsAirePar("MID", "SVCS")
//   val itsa2 = itsAirePar("CLO", "SVCS")

//   val itsa3 = itsAirePar("CLO", "SVO")

//   val itsa4 = itsAirePar("CLO", "MEX")

//   val itsa5 = itsAirePar("CTG", "PTY")

//   println("\n")
//   println(itsa1)
//   println("\n")
//   println(itsa2)
//   println("\n")
//   println(itsa3)
//   println("\n")
//   println(itsa4)
//   println("\n")
//   println(itsa5)
//   println("\n")


//   val itsPar15A1 = itinerariosPar(vuelosA1, aeropuertos)
//   val itsTpoPar15A1 = itinerariosTiempoPar(vuelosA1, aeropuertos)
//   val itsEscPar15A1 = itinerariosEscalasPar(vuelosA1, aeropuertos)
//   val itsAirPar15A1 = itinerariosAirePar(vuelosA1, aeropuertos)
//   val itsSalPar15A1 = itinerarioSalidaPar(vuelosA1, aeropuertos)
//   println("Pruenbas con 15 vuelos")
//   println(itsPar15A1("HOU", "BNA"))
//   println("\n")
//   println(itsTpoPar15A1("HOU", "BNA"))
//   println("\n")
//   println(itsEscPar15A1("HOU", "BNA"))
//   println("\n")
//   println(itsAirPar15A1("HOU", "BNA"))
//   println("\n")
//   println(itsSalPar15A1("HOU", "BNA", 18, 30))

//   val itsPar40B1 = itinerariosPar(vuelosB1, aeropuertos)
//   val itsTpoPar40B1 = itinerariosTiempoPar(vuelosB1, aeropuertos)
//   val itsEscPar40B1 = itinerariosEscalasPar(vuelosB1, aeropuertos)
//   val itsAirPar40B1 = itinerariosAirePar(vuelosB1, aeropuertos)
//   val itsSalPar40B1 = itinerarioSalidaPar(vuelosB1, aeropuertos)

//   println("Pruenbas con 40 vuelos")
//   println("\n")
//   println(itsPar40B1("DFW", "ORD"))
//   println("\n")
//   println(itsTpoPar40B1("DFW", "ORD"))
//   println("\n")
//   println(itsEscPar40B1("DFW", "ORD"))
//   println("\n")
//   println(itsAirPar40B1("DFW", "ORD"))
//   println("\n")
//   println(itsSalPar40B1("DFW", "ORD", 18, 30))
//   println("\n")

//   val itsPar100C1 = itinerariosPar(vuelosC1, aeropuertos)
//   val itsTpoPar100C1 = itinerariosTiempoPar(vuelosC1, aeropuertos)
//   val itsEscPar100C1 = itinerariosEscalasPar(vuelosC1, aeropuertos)
//   val itsAirPar100C1 = itinerariosAirePar(vuelosC1, aeropuertos)
//   val itsSalPar100C1 = itinerarioSalidaPar(vuelosC1, aeropuertos)

//   println("Pruenbas con 100 vuelos")
//   println("\n")
//   println(itsPar100C1("ORD", "TPA"))
//   println("\n")
//   println(itsTpoPar100C1("ORD", "TPA"))
//   println("\n")
//   println(itsEscPar100C1("ORD", "TPA"))
//   println("\n")
//   println(itsAirPar100C1("ORD", "TPA"))
//   println("\n")
//   println(itsSalPar100C1("ORD", "TPA", 18, 30))
//   println("\n")

  // val itsPar200C = itinerariosPar(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsTpoPar200C = itinerariosTiempoPar(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsEscPar200C = itinerariosEscalasPar(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsAirPar200C = itinerariosAirePar(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsSalPar200C = itinerarioSalidaPar(vuelosC1 ++ vuelosC2, aeropuertos)

//   println("\n")
//   println("Pruenbas con 200 vuelos")
//   println("\n")
//   println(itsPar200C("ORD", "TPA"))
//   println("\n")
//   println(itsTpoPar200C("ORD", "TPA"))
//   println("\n")
//   println(itsEscPar200C("ORD", "TPA"))
//   println("\n")
//   println(itsAirPar200C("ORD", "TPA"))
//   println("\n")
//   println(itsSalPar200C("ORD", "TPA", 18, 30))
//   println("\n")

  // val its300CPar =
  //   itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3), aeropuertos)

  // println("Pruenbas con 300 vuelos")
  // println("\n")
  // println(its300CPar("ORD", "TPA"))

  // val its400CPar =
  //   itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4), aeropuertos)

  // println("Pruenbas con 400 vuelos")
  // println("\n")
  // println(its400CPar("ORD", "TPA"))

  // val its500CPar = itinerariosPar(
  //   (vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5),
  //   aeropuertos
  // )
  // println("Pruenbas con 500 vuelos")
  // println("\n")
  // println(its500CPar("ORD", "TPA"))

  // val its400C =
  //   itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4, aeropuertos)

  // println(its400C("ORD", "TPA"))

  // val its300C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3, aeropuertos)
  // println(its300C("ORD", "TPA"))

//   val itsCurso = itinerarios(vuelosCurso, aeropuertosCurso)
// //2.1 Aeropuertos incomunicados
//   val its1 = itsCurso("MID", "SVCS")
//   val its2 = itsCurso("CLO", "SVCS")

// // 4 itinerarios CLO-SVO

//   val its3 = itsCurso("CLO", "SVO")

// //2 itinerarios CLO-MEX

//   val its4 = itsCurso("CLO", "MEX")

// //2 itinerarios CTG-PTY
//   val its5 = itsCurso("CTG", "PTY")

//   println("\n")
//   println(its1)
//   println("\n")
//   println(its2)
//   println("\n")
//   println(its3)
//   println("\n")
//   println(its4)
//   println("\n")
//   println(its5)
//   println("\n")

//   val itsTiempoCurso = itinerariosTiempo(vuelosCurso, aeropuertosCurso)

// // prueba itinerariosTiempo
//   val itst1 = itsTiempoCurso("MID", "SVCS")
//   val itst2 = itsTiempoCurso("CLO", "SVCS")

// // 4 itinerarios CLO-SVO

//   val itst3 = itsTiempoCurso("CLO", "SVO")

// //2 itinerarios CLO-MEX

//   val itst4 = itsTiempoCurso("CLO", "MEX")

// //2 itinerarios CTG-PTY
//   val itst5 = itsTiempoCurso("CTG", "PTY")

//   println("\n")
//   println(itst1)
//   println("\n")
//   println(itst2)
//   println("\n")
//   println(itst3)
//   println("\n")
//   println(itst4)
//   println("\n")
//   println(itst5)
//   println("\n")

// // prueba itinerarioSalida
//   val itSalidaCurso = itinerarioSalida(vuelosCurso, aeropuertosCurso)

//   val itsal1 = itSalidaCurso("CTG", "PTY", 11, 40)
//   val itsal2 = itSalidaCurso("CTG", "PTY", 11, 55)
//   val itsal3 = itSalidaCurso("CTG", "PTY", 10, 30)

//   println("\n")
//   println(itsal1)
//   println("\n")
//   println(itsal2)
//   println("\n")
//   println(itsal3)
//   println("\n")

// println("\n")
// println(itinerarios(vuelosA1,aeropuertos)("HOU","BNA"))
// println("\n")
// println(itinerariosTiempo(vuelosA1,aeropuertos)("HOU","BNA"))
// println("\n")
// println(itinerariosEscalas(vuelosA1,aeropuertos)("HOU","BNA"))
// println("\n")
// println(itinerariosAire(vuelosA1,aeropuertos)("HOU","BNA"))
// println("\n")
// println(itinerarioSalida(vuelosA1,aeropuertos)("HOU","BNA", 18, 30))
// println("\n")

// val its40B1 = itinerarios(vuelosB1,aeropuertos)
// val itsTpo40B1 = itinerariosTiempo(vuelosB1,aeropuertos)
// val itsEsc40B1 = itinerariosEscalas(vuelosB1,aeropuertos)
// val itsAir40B1 = itinerariosAire(vuelosB1,aeropuertos)
// val itsSal40B1 = itinerarioSalida(vuelosB1,aeropuertos)

// println("\n")
// println(its40B1("DFW","ORD"))
// println("\n")
// println(itsTpo40B1("DFW","ORD"))
// println("\n")
// println(itsEsc40B1("DFW","ORD"))
// println("\n")
// println(itsAir40B1("DFW","ORD"))
// println("\n")
// println(itsSal40B1("DFW","ORD", 18, 30))
// println("\n")

// val its100C1 = itinerarios(vuelosC1,aeropuertos)
// val itsTpo100C1 = itinerariosTiempo(vuelosC1,aeropuertos)
// val itsEsc100C1 = itinerariosEscalas(vuelosC1,aeropuertos)
// val itsAir100C1 = itinerariosAire(vuelosC1,aeropuertos)
// val itsSal100C1 = itinerarioSalida(vuelosC1,aeropuertos)

// println("\n")
// println(its100C1("ORD","TPA"))
// println("\n")
// println(itsTpo100C1("ORD","TPA"))
// println("\n")
// println(itsEsc100C1("ORD","TPA"))
// println("\n")
// println(itsAir100C1("ORD","TPA"))
// println("\n")
// println(itsSal100C1("ORD","TPA", 18, 30))
// println("\n")

  // val its200C = itinerarios(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsTpo200C = itinerariosTiempo(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsEsc200C = itinerariosEscalas(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsAir200C = itinerariosAire(vuelosC1 ++ vuelosC2, aeropuertos)
  // val itsSal200C = itinerarioSalida(vuelosC1 ++ vuelosC2, aeropuertos)

  // println(compararAlgoritmos(itinerarios, itinerariosPar)(vuelosC1 ++ vuelosC2, aeropuertos)("ORD", "TPA"))

// println("\n")
// println(its200C("ORD","TPA"))
// println("\n")
// println(itsTpo200C("ORD","TPA"))
// println("\n")
// println(itsEsc200C("ORD","TPA"))
// println("\n")
// println(itsAir200C("ORD","TPA"))
// println("\n")
// println(itsSal200C("ORD","TPA", 18, 30))
// println("\n")

  // val its300C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3, aeropuertos)
  // println("\n")
  // println(its300C("ORD", "TPA"))

  // val its400C =
  //   itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4, aeropuertos)
  // println("\n")
  // println(its400C("ORD", "TPA"))

  // val its500C = itinerarios(
  //   vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5,
  //   aeropuertos
  // )
  // println("\n")
  // println(its500C("ORD", "TPA"))

}
