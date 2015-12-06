package eu.keios.AdventOfCode

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
