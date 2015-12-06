package eu.keios.AdventOfCode

object Main extends App {

  val day1 = Task1a :: Task1b :: Nil
  val day2 = Task2a :: Task2b :: Nil
  val day3 = Task3a :: Task3b :: Nil

  val allTasks = day1 ++ day2 ++ day3

  allTasks.map { task =>
    (task.getClass.getName, task.result)
  }.foreach { result =>
    println(s"${result._1}: ${result._2.toString}")
  }
}

trait AdventOfCodeTask {
  def result: Any
}