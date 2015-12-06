package eu.keios.AdventOfCode

import java.security.MessageDigest
import scala.annotation.tailrec

object Task4a extends Task4a {
  def result = findLowestHashCoefficientForKey(testInput)
}

trait Task4a extends AdventOfCodeTask {

  val hashStartKey = "00000"

  def testInput = "bgvyzdsv"

  def md5(s: String): String = {
    MessageDigest.getInstance("MD5").digest(s.getBytes).map("%02x".format(_)).mkString
  }

  def findLowestHashCoefficientForKey(key: String): Long = {

    @tailrec def recFindCoefficient(key: String, currentNo: Long): Long = {
      val hash = md5(key + currentNo)
      if (hash.startsWith(hashStartKey)) {
        currentNo
      } else {
        recFindCoefficient(key, currentNo + 1L)
      }
    }

    recFindCoefficient(key, 1L)
  }

}
