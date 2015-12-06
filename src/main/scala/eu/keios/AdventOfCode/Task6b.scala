package eu.keios.AdventOfCode

//  --- Part Two ---
//
//  You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from Ancient Nordic Elvish.
//
//  The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.
//
//  The phrase turn on actually means that you should increase the brightness of those lights by 1.
//
//  The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
//
//  The phrase toggle actually means that you should increase the brightness of those lights by 2.
//
//  What is the total brightness of all lights combined after following Santa's instructions?
//
//  For example:
//
//  turn on 0,0 through 0,0 would increase the total brightness by 1.
//  toggle 0,0 through 999,999 would increase the total brightness by 2000000.

object Task6b extends Task6b {
  def result = {
    val tbl = getSquareTableWithBrightness(1000)

    taskInput.foreach { iStr =>
      applyInstruction(tbl, parseInstruction(iStr))
    }

    countLitLights(tbl)
  }
}

trait Task6b extends Task6a {

  import scala.Array._

  type ChristmasLightsMatrixWithBrightness = Array[Array[Int]]

  def getSquareTableWithBrightness(side: Int): ChristmasLightsMatrixWithBrightness = {
    val tbl = ofDim[Int](side, side)
    for (i <- 0 to side - 1) {
      for (j <- 0 to side - 1) {
        tbl(i)(j) = 0
      }
    }

    tbl
  }

  def applyInstruction(tbl: ChristmasLightsMatrixWithBrightness, ins: Instruction): Unit = {
    for (i <- ins.sx to ins.ex) {
      for (j <- ins.sy to ins.ey) {
        ins match {
          case _: Toggle => tbl(i)(j) = tbl(i)(j) + 2
          case _: TurnOff => tbl(i)(j) = if (tbl(i)(j) - 1 > -1) tbl(i)(j) - 1 else 0
          case _: TurnOn => tbl(i)(j) = tbl(i)(j) + 1
        }
      }
    }
  }

  def countLitLights(tbl: ChristmasLightsMatrixWithBrightness) = tbl.foldLeft(0)(_ + _.sum)
}