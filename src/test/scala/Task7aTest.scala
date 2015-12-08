import org.scalatest.{Matchers, FlatSpec}

class Task7aTest extends FlatSpec with Matchers {

  import eu.keios.AdventOfCode.Task7a._

  val input = "123 -> x\n456 -> y\nx AND y -> d\nx OR y -> e\nx LSHIFT 2 -> f\ny RSHIFT 2 -> g\nNOT x -> h\nNOT y -> i".split("\n").toList

  "Circuit builder" should "be able to construct circuit correctly" in {
    val map = Circuit.run(input)

    map("d") should be(72)
    map("e") should be(507)
    map("f") should be(492)
    map("g") should be(114)
    map("h") should be(65412)
    map("i") should be(65079)
    map("x") should be(123)
    map("y") should be(456)
  }

}
