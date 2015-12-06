package eu.keios.AdventOfCode

//  --- Part Two ---
//
//  The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
//
//  Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
//
//  This year, how many houses receive at least one present?
//
//  For example:
//
//  ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
//  ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
//  ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.

object Task3b extends Task3b {

  def result = countHousesVisitedWithRoboSanta(testInput)

}

trait Task3b extends Task3a {

  def countHousesVisitedWithRoboSanta(directions: String): Int = {
    countVisitedHouses(
      splitDirections(directions).productIterator.map {
        case p: String => parseDirections(p)
      }.foldLeft(List.empty[House]) {
        _ ++ _
      }
    )
  }

  def splitDirections(directions: String): (String, String) = {
    directions.zipWithIndex.map { t =>
      if (t._2 % 2 == 0) {
        Left(t._1)
      } else {
        Right(t._1)
      }
    }.foldLeft(("", "")) { (acc, curr) =>
      curr match {
        case Left(char) => (acc._1 + char, acc._2)
        case Right(char) => (acc._1, acc._2 + char)
      }
    }
  }

}
