import Datos._
import Itinerarios._
import ItinerariosPar._
import org.scalameter._


package object Benchmark {
    type AlgoritmoItinerario = (List[Vuelo], List[Aeropuerto]) => (String, String) => List[Itinerario]
    type AlgoritmoItinerarioSalida = (List[Vuelo], List[Aeropuerto]) => (String, String, Int, Int) => Itinerario

    def compararAlgoritmos(
        a1: AlgoritmoItinerario,
        a2: AlgoritmoItinerario,
    )(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto])(cod1: String, cod2: String): (Double, Double, Double) = {
        val tiempoA1 = config(
            KeyValue(Key.exec.minWarmupRuns -> 20),
            KeyValue(Key.exec.maxWarmupRuns -> 60),
            KeyValue(Key.verbose -> false)
        ) withWarmer ( new Warmer.Default ) measure (a1(vuelos, aeropuertos)(cod1, cod2))

        val tiempoA2 = config(
            KeyValue(Key.exec.minWarmupRuns -> 20),
            KeyValue(Key.exec.maxWarmupRuns -> 60),
            KeyValue(Key.verbose -> false)
        ) withWarmer ( new Warmer.Default ) measure (a2(vuelos, aeropuertos)(cod1, cod2))

        (tiempoA1.value, tiempoA2.value, tiempoA1.value / tiempoA2.value)
    }

    def compararAlgoritmosSalida(
        a1: AlgoritmoItinerarioSalida,
        a2: AlgoritmoItinerarioSalida,
    )(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto])(cod1: String, cod2: String, h: Int, m: Int): (Double, Double, Double) = {
        val tiempoA1 = config(
            KeyValue(Key.exec.minWarmupRuns -> 20),
            KeyValue(Key.exec.maxWarmupRuns -> 60),
            KeyValue(Key.verbose -> false)
        ) withWarmer ( new Warmer.Default ) measure (a1(vuelos, aeropuertos)(cod1, cod2, h, m))

        val tiempoA2 = config(
            KeyValue(Key.exec.minWarmupRuns -> 20),
            KeyValue(Key.exec.maxWarmupRuns -> 60),
            KeyValue(Key.verbose -> false)
        ) withWarmer ( new Warmer.Default ) measure (a2(vuelos, aeropuertos)(cod1, cod2, h, m))

        (tiempoA1.value, tiempoA2.value, tiempoA1.value / tiempoA2.value)
    }
}
