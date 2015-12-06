import org.scalatest.{Matchers, FlatSpec}

class Task6aTest extends FlatSpec with Matchers {

  import eu.keios.AdventOfCode.Task6a._

  val i1 = "turn on 0,0 through 999,999"
  val i2 = "toggle 0,0 through 999,0"
  val i3 = "turn off 499,499 through 500,500"

  "Table generating function" should "generate table of lights" in {
    val tbl = getSquareTable(999)

    tbl(1)(10) = 1
    tbl(2)(5) = 1
    tbl(342)(343) = 1

    countLitLights(tbl) should be(3)
  }

  "Instructions parsing function" should "parse instructions correctly" in {
    parseInstruction(i1) should be(TurnOn(0, 0, 999, 999))
    parseInstruction(i2) should be(Toggle(0, 0, 999, 0))
    parseInstruction(i3) should be(TurnOff(499, 499, 500, 500))
  }

  "Table management via instructions" should "yield correct numbers of lit lights" in {
    val tbl = getSquareTable(1000)

    applyInstruction(tbl, parseInstruction(i1))

    countLitLights(tbl) should be (1000000)

    applyInstruction(tbl, parseInstruction(i2))

    countLitLights(tbl) should be (1000000 - 1000)

    applyInstruction(tbl, parseInstruction(i3))

    countLitLights(tbl) should be (1000000 - 1000 - 4)
  }
}
