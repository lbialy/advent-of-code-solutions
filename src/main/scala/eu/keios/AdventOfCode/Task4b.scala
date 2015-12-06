package eu.keios.AdventOfCode

//  --- Part Two ---
//
//  Now find one that starts with six zeroes.

object Task4b extends Task4a {

  override val hashStartKey = "000000"

  def result = findLowestHashCoefficientForKey(testInput)

}
