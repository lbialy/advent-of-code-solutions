import eu.keios.AdventOfCode.Task2b.Present
import eu.keios.AdventOfCode.Task2b.ribbonLength
import org.scalatest.{Matchers, FlatSpec}

class Task2bTest extends FlatSpec with Matchers {

  val examplePresents = List[(Present, Int)](
    (Present(2, 3, 4), 34),
    (Present(1, 1, 10), 14)
  )

  "Ribbon length counting function" should "return correct length of required ribbon" in {

    examplePresents.foreach { tuple =>
      ribbonLength(tuple._1) should be(tuple._2)
    }
  }

}
