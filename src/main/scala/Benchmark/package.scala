import org.scalameter._
import Datos._
import Itinerarios._
import ItinerariosPar._

package object Benchmark {

  def tiempoItinerarios(): Unit = {

    val tItinerarios = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer(new Warmer.Default) measure {
      itinerarios(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val tItinerariosPar = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosPar(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val speedUp= tItinerarios.value/tItinerariosPar.value

    println(s"Tiempo para Itinerarios: $tItinerarios")
    println(s"Tiempo para ItinerariosPar: $tItinerariosPar")
    println(s"El speedup es: $speedUp")
  }

  def tiempoItinerariosTiempo(): Unit = {

    val tItinerariosTiempo = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosTiempoPar(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val tItinerariosTiempoPar = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosTiempoPar(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val speedUp = tItinerariosTiempo.value / tItinerariosTiempoPar.value

    println(s"Tiempo para ItinerariosTiempo: $tItinerariosTiempo")
    println(s"Tiempo para ItinerariosTiempoPar: $tItinerariosTiempoPar")
    println(s"El speedup es: $speedUp")
  }

  def tiempoItinerariosEscalas(): Unit = {

    val tItinerariosEscalas = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosEscalas(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val tItinerariosEscalasPar = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosEscalasPar(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val speedUp = tItinerariosEscalas.value / tItinerariosEscalasPar.value

    println(s"Tiempo para ItinerariosEscalas: $tItinerariosEscalas")
    println(s"Tiempo para ItinerariosEscalasPar: $tItinerariosEscalasPar")
    println(s"El speedup es: $speedUp")
  }

  def tiempoItinerariosAire(): Unit = {

    val tItinerariosAire = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosAire(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val tItinerariosAirePar = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosAirePar(vuelosCurso, aeropuertosCurso)("CLO", "BOG")
    }

    val speedUp = tItinerariosAire.value / tItinerariosAirePar.value

    println(s"Tiempo para ItinerariosAire: $tItinerariosAire")
    println(s"Tiempo para ItinerariosAirePar: $tItinerariosAirePar")
    println(s"El speedup es: $speedUp")
  }

  def tiempoItinerariosSalida(): Unit = {

    val tItinerariosSalida = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosSalida(vuelosCurso, aeropuertosCurso)("CLO", "SVO",10,0)
    }

    val tItinerariosAirePar = config(
      KeyValue(Key.exec.minWarmupRuns -> 10),
      KeyValue(Key.exec.maxWarmupRuns -> 50),
      KeyValue(Key.verbose -> false)
    ) withWarmer (new Warmer.Default) measure {
      itinerariosSalidaPar(vuelosCurso, aeropuertosCurso)("CLO", "SVO",10,0)
    }

    val speedUp = tItinerariosSalida.value / tItinerariosAirePar.value

    println(s"Tiempo para ItinerariosSalida: $tItinerariosSalida")
    println(s"Tiempo para ItinerariosSalidaPar: $tItinerariosAirePar")
    println(s"El speedup es: $speedUp")
  }
}
