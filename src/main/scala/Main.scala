object MainApp {
  def main(args: Array[String]): Unit = {
    Benchmark.tiempoItinerarios()
    Benchmark.tiempoItinerariosTiempo()
    Benchmark.tiempoItinerariosEscalas()
    Benchmark.tiempoItinerariosAire()
    Benchmark.tiempoItinerariosSalida()
  }
}
