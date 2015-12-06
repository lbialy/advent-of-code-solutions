import org.scalatest.{Matchers, FlatSpec}
import eu.keios.AdventOfCode.Task2a._

class Task2aTest extends FlatSpec with Matchers {

  val examplePresents = List[(Present, Int)](
    (Present(2, 3, 4), 58),
    (Present(1, 1, 10), 43)
  )

  "Present wrapping counting function" should "return correct amount of paper required" in {
    examplePresents.foreach { tuple =>
      getPresentWrappingSize(tuple._1) should be (tuple._2)
    }
  }

}
