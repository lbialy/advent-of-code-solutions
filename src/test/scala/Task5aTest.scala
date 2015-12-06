import eu.keios.AdventOfCode.Task5a
import org.scalatest.{Matchers, FlatSpec}

class Task5aTest extends FlatSpec with Matchers {

  import eu.keios.AdventOfCode.Task5a._

  val nicelyVoweledStringsExamples = List(
    "aei",
    "xazegov",
    "aeiouaeiouaeiou"
  )

  val nicelyDoubledStringsExamples = List(
    "xx",
    "abcdde",
    "aabbccdd"
  )

  val naughtyString = "haegwjzuvuyypxyu"
  val niceString = "ugknbfddgicrmopn"

  // string, is naughty or not
  val stringExamples = List[(String, Boolean)](
    ("ugknbfddgicrmopn", true),
    ("aaa", true),
    ("jchzalrnumimnmhp", false),
    ("haegwjzuvuyypxyu", false),
    ("dvszwmarrgswjxmb", false)
  )

  "Vowel checking function" should "be able to determine if string has at least 3 vowels from aeiou set" in {
    nicelyVoweledStringsExamples.foreach {
      hasAtLeastThreeNiceVowels(_) should be(true)
    }
  }

  "Pair checking function" should "be able to check if there is at least one pair in string" in {
    nicelyDoubledStringsExamples.foreach {
      hasAtLeastOneNicePair(_) should be(true)
    }
  }

  "Naughty pairs checking function" should "detect naughty pairs in strings" in {
    doesNotContainAnyNaughtyStrings(naughtyString) should be(false)
    doesNotContainAnyNaughtyStrings(niceString) should be(true)
  }

  "Naughty string checking function" should "detect if string is naughty" in {
    stringExamples.foreach { tuple =>
      tuple._1.isNice should be(tuple._2)
    }
  }

}
