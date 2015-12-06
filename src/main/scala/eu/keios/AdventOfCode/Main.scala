package eu.keios.AdventOfCode

object Main extends App {

  val day1 = Task1a :: Task1b :: Nil
  val day2 = Task2a :: Task2b :: Nil
  val day3 = Task3a :: Task3b :: Nil
  val day4 = Task4a :: Task4b :: Nil
  val day5 = Task5a :: Task5b :: Nil

  val allTasks = day1 ++ day2 ++ day3 ++ day4 ++ day5

  allTasks.map { task =>
    val start = System.currentTimeMillis().toDouble
    val result = task.result
    val stop = System.currentTimeMillis().toDouble

    (task.getClass.getName, result, (stop - start)/1000)
  }.foreach { result =>
    val name = result._1.replaceFirst("eu.keios.AdventOfCode.", "").stripSuffix("$")
    println(s"$name: ${result._2.toString} - took ${result._3}s")
  }
}

trait AdventOfCodeTask {
  def result: Any
}