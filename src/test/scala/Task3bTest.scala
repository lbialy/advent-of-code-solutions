import org.scalatest.{Matchers, FlatSpec}

class Task3bTest extends FlatSpec with Matchers {
  // directions, houses count
  val directionsExamples = List[(String, Int)](
    ("^v", 3),
    ("^>v<", 3),
    ("^v^v^v^v^v", 11)
  )

  "House visit counting function" should "return count of houses visited at least once" in {
    import eu.keios.AdventOfCode.Task3b._

    directionsExamples.foreach { tuple =>
      countHousesVisitedWithRoboSanta(tuple._1) should be (tuple._2)
    }
  }
}
