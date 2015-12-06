package eu.keios.AdventOfCode

import scala.annotation.tailrec

object Task1b extends Task1b with Task1a {

  def result: Any = whenFirstInBasement(taskData).fold("Never") {
    _.toString
  }

}

trait Task1b {
  this: Task1a =>

  def whenFirstInBasement(instructions: String): Option[Int] = {

    @tailrec def countTillBasement(instructions: String, count: Int, position: Int): Option[Int] = {
      if (count == -1) {
        Some(position) // went to basement
      } else if (instructions.head.equals('(')) {
        countTillBasement(instructions.tail, count + 1, position + 1)
      } else if (instructions.head.equals(')')) {
        countTillBasement(instructions.tail, count - 1, position + 1)
      } else {
        None // never goes to basement
      }
    }

    countTillBasement(instructions, 0, 0)
  }

}
