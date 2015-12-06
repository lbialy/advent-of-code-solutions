import org.scalatest.{Matchers, FlatSpec}

class Task4aTest extends FlatSpec with Matchers {

  // key, matching hash
  val hashExamples = List[(String, Long)](
    ("abcdef", 609043L),
    ("pqrstuv", 1048970L)
  )

  "Coefficient mining function" should "return lowest MD5 hashing coefficient for key" in {
    import eu.keios.AdventOfCode.Task4a._

    hashExamples.foreach { tuple =>
      println(s"Starting searching for hash coefficient for key ${tuple._1}...")
      val start = System.currentTimeMillis().toDouble
      findLowestHashCoefficientForKey(tuple._1) should be (tuple._2)
      val stop = System.currentTimeMillis().toDouble
      println(s"Ended searching for hash coefficient for key ${tuple._1} after ${(stop-start)/1000}s.")
    }
  }
}
