package eu.keios.AdventOfCode

object Task4b extends Task4a {

  override val hashStartKey = "000000"

  def result = findLowestHashCoefficientForKey(testInput)

}
