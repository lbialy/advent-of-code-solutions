package eu.keios.AdventOfCode

//  --- Part Two ---
//
//  Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.
//
//  Now, a nice string is one with all of the following properties:
//
//  It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
//  It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
//  For example:
//
//  qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
//  xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
//  uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
//  ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
//  How many strings are nice under these new rules?

import scala.annotation.tailrec

object Task5b extends Task5b {

  def result = taskInput.split("\n").map{ _.isNiceInThisYearToo }.count(identity)

}

trait Task5b extends Task5a {

  def containsAtLeastOneNicePair(s: String): Boolean = {

    @tailrec def recFindNonOverlappingPairs(list: List[String], found: List[String]): List[String] = {
      // "aaaa"
      // aa aa aa Nil
      // "xxxyx"
      // xx xx xy yx Nil
      list match {
        case first :: second :: third :: xs =>
          if (first == second && first != third) { // xx == xx && xx != xy // aa == aa && aa != aa
            recFindNonOverlappingPairs(third :: xs, found)
          } else if (first == second && first == third) { // xx == xx && xx == xy // aa == aa && aa == aa
            recFindNonOverlappingPairs(xs, first :: third :: found)
          } else {
            recFindNonOverlappingPairs(second :: third :: xs, first :: found)
          }
        case first :: second :: xs =>
          if (first == second) { // xx == xx // aa == aa
            recFindNonOverlappingPairs(xs, found)
          } else {
            recFindNonOverlappingPairs(second :: xs, first :: found)
          }
        case x :: Nil => recFindNonOverlappingPairs(Nil, x :: found)
        case Nil => found
      }
    }

    recFindNonOverlappingPairs(s.sliding(2).toList, Nil)
      .groupBy(identity)
      .mapValues(_.size)
      .count {
        t => t._2 > 1
      } > 0
  }

  def containsAtLeastOneDividedPair(s: String): Boolean = {
    def matchDividedPair: PartialFunction[List[Char], Boolean] = {
      case first :: second :: third :: Nil => if (first == third) true else false
      case _ => false
    }

    s.sliding(3)
      .map(_.toList)
      .map {
        matchDividedPair
      }
      .contains(true)
  }

  implicit class UpdatedSelfAssessingString(s: String) {
    def isNiceInThisYearToo: Boolean = containsAtLeastOneDividedPair(s) && containsAtLeastOneNicePair(s)
  }

}
