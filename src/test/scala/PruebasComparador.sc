import Datos._
import ItinerariosPar._
import Itinerarios._
import Benchmark.compararAlgoritmos
import Benchmark.compararAlgoritmosSalida

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
    compararAlgoritmos(itinerarios, itinerariosPar)(vuelos60, aeropuertos)("HOU","BNA")
    compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos60, aeropuertos)("HOU","BNA")
    compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos60, aeropuertos)("HOU","BNA")
    compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos60, aeropuertos)("HOU","BNA")
    compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos60, aeropuertos)("HOU","BNA", 18, 30)

    println("Prueba con una lista de 100 vuelos")
    compararAlgoritmos(itinerarios, itinerariosPar)(vuelos100, aeropuertos)("DFW","ORD")
    compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos100, aeropuertos)("DFW","ORD")
    compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos100, aeropuertos)("DFW","ORD")
    compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos100, aeropuertos)("DFW","ORD")
    compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos100, aeropuertos)("DFW","ORD", 18, 30)

    println("Prueba con una lista de 160 vuelos")
    compararAlgoritmos(itinerarios, itinerariosPar)(vuelos160, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos160, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos160, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos160, aeropuertos)("ORD","TPA")
    compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos160, aeropuertos)("ORD","TPA", 18, 30)

    println("Prueba con una lista de 200 vuelos")
    compararAlgoritmos(itinerarios, itinerariosPar)(vuelos200, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosTiempo, itinerariosTiempoPar)(vuelos200, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosEscalas, itinerariosEscalasPar)(vuelos200, aeropuertos)("ORD","TPA")
    compararAlgoritmos(itinerariosAire, itinerariosAirePar)(vuelos200, aeropuertos)("ORD","TPA")
    compararAlgoritmosSalida(itinerarioSalida, itinerarioSalidaPar)(vuelos200, aeropuertos)("ORD","TPA", 18, 30)
}