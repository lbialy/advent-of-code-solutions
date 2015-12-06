package eu.keios.AdventOfCode

object Task2b extends Task2b with Task2a {

  def result = getPresents.map {
    ribbonLength
  }.sum

}

trait Task2b extends AdventOfCodeTask {
  this: Task2a =>

  def ribbonLength(p: Present) = p.wrap + p.bow

  implicit class ExtendedPresent(p: Present) {
    def bow = p.h * p.w * p.l

    def wrap = {
      val smallSides = List(p.w, p.l, p.h).sorted.take(2)
      smallSides.head + smallSides.head + smallSides(1) + smallSides(1)
    }
  }
}
