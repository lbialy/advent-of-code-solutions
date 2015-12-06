package eu.keios.AdventOfCode

//  --- Part Two ---
//
//  Now, given the same instructions, find the position of the first character that causes him to enter the basement (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.
//
//  For example:
//
//  ) causes him to enter the basement at character position 1.
//  ()()) causes him to enter the basement at character position 5.
//  What is the position of the character that causes Santa to first enter the basement?

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
