import eu.keios.AdventOfCode.Task1a
import org.scalatest.{Matchers, FlatSpec}

class Task1aTest extends FlatSpec with Matchers {

  // example, remaining length, result
  val cancellingOutExamples = List[(String, Int, Int)](
    ("(())", 0, 0),
    ("()()", 0, 0),
    ("(()(()(", 3, 3),
    (")())())", 3, -3)
  )

  "Parenthesis cancelling out function" should "remove paired parentheses" in {
    cancellingOutExamples.foreach { tuple =>
      Task1a.cancelOut(tuple._1).length should be(tuple._2)
    }
  }

  "Suming function" should "be able to return correct value of remaining parentheses" in {
    import Task1a._
    cancellingOutExamples.foreach { tuple =>
      (sum _ compose cancelOut) (tuple._1) should be(tuple._3)
    }
  }

}
