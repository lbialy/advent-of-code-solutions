import org.scalatest.{Matchers, FlatSpec}

class Task5bTest extends FlatSpec with Matchers {

  import eu.keios.AdventOfCode.Task5b._

  val pairExamples = List[(String, Boolean)](
    ("xyxy", true),
    ("aabcdefgaa", true),
    ("aaaa", true),
    ("aaa", false),
    ("abc", false)
  )

  val dividedPairsExamples = List(
    "xyx",
    "abcdefeghi",
    "aaa"
  )

  // string, is nice or not
  val stringExamples = List[(String, Boolean)](
    ("qjhvhtzxzqqjkmpb", true),
    ("xxyxx", true),
    ("uurcxstgmygtbstg", false),
    ("ieodomkazucvgmuy", false),
    ("cccc", true)
  )

  "Nice pair detecting function" should "be able to tell if string contains nice pairs" in {
    pairExamples.foreach { tuple =>
      containsAtLeastOneNicePair(tuple._1) should be(tuple._2)
    }
  }

  "Divided pair detecting function" should "be able to find divided pairs in string" in {
    dividedPairsExamples.foreach {
      containsAtLeastOneDividedPair(_) should be(true)
    }
  }

  "Nice string detecting function" should "be able to tell if string is nice or naugthy" in {
    stringExamples.foreach { tuple =>
      tuple._1.isNiceInThisYearToo should be(tuple._2)
    }
  }
}
