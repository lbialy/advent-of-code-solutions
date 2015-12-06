import org.scalatest.{Matchers, FlatSpec}
import eu.keios.AdventOfCode.Task1b

class Task1bTest extends FlatSpec with Matchers {

  val examples = List[(String, Int)](
    (")", 1),
    ("()())", 5)
  )

  "whenFirstInBasement function" should "return correct position when Santa goes to -1 floor" in {
    examples.foreach { tuple =>
      Task1b.whenFirstInBasement(tuple._1).getOrElse(0) should be(tuple._2)
    }
  }
}
