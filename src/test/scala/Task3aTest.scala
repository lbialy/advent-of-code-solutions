import org.scalatest.{Matchers, FlatSpec}

class Task3aTest extends FlatSpec with Matchers {

  // directions, houses count
  val directionsExamples = List[(String, Int)](
    (">", 2),
    ("^>v<", 4),
    ("^v^v^v^v^v", 2)
  )

  "House visit counting function" should "return count of houses visited at least once" in {
    import eu.keios.AdventOfCode.Task3a._

    def testFun = countVisitedHouses _ compose parseDirections

    directionsExamples.foreach { tuple =>
      testFun(tuple._1) should be(tuple._2)
    }
  }

}
