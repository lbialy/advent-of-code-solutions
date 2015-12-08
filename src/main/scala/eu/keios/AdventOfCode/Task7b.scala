package eu.keios.AdventOfCode

import scala.annotation.tailrec

//  --- Part Two ---
//
//  Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a).
//  What new signal is ultimately provided to wire a?

object Task7b extends Task7b {
  def result = OverriddenCircuit.run(testInput)("a")
}

trait Task7b extends Task7a {

  object OverriddenCircuit {

    def run(lst: List[String]) = {

      val start = System.currentTimeMillis()

      @tailrec def resolveAll(resolved: Map[String, Int], unresolved: List[Gate]): Map[String, Int] = {

        if (System.currentTimeMillis() - start > 3000) {
          resolved.foreach(println)
          throw new RuntimeException("Infinite recursion.")
        }

        unresolved.headOption match {
          case Some(Gate(name, operation)) =>
            operation match {
              case Input(value) =>
                //                println(s"Resolved input value $value for gate $name")
                resolveAll(resolved + (name -> value), unresolved.tail)
              case op@Connection(key) =>
                if (resolved.isDefinedAt(key)) {
                  //                  println(s"Resolved connection for key $key with value ${resolved(key)}")
                  resolveAll(resolved + (name -> resolved(key)), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
              case op@And(lOp, rOp) =>
                if (resolved.isDefinedAt(lOp) && resolved.isDefinedAt(rOp)) {
                  //                  println(s"Resolved AND for keys $lOp AND $rOp with value ${op.resolve(resolved(lOp), resolved(rOp))}")
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp), resolved(rOp))), unresolved.tail)
                } else if (lOp.forall(Character.isDigit) && rOp.forall(Character.isDigit)) {
                  resolveAll(resolved + (name -> op.resolve(lOp.toInt, rOp.toInt)), unresolved.tail)
                } else if (lOp.forall(Character.isDigit) && resolved.isDefinedAt(rOp)) {
                  resolveAll(resolved + (name -> op.resolve(lOp.toInt, resolved(rOp))), unresolved.tail)
                } else if (rOp.forall(Character.isDigit) && resolved.isDefinedAt(lOp)) {
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp), rOp.toInt)), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
              case op@Or(lOp, rOp) =>
                if (resolved.isDefinedAt(lOp) && resolved.isDefinedAt(rOp)) {
                  //                  println(s"Resolved OR for keys $lOp AND $rOp with value ${op.resolve(resolved(lOp), resolved(rOp))}")
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp), resolved(rOp))), unresolved.tail)
                } else if (lOp.forall(Character.isDigit) && rOp.forall(Character.isDigit)) {
                  resolveAll(resolved + (name -> op.resolve(lOp.toInt, rOp.toInt)), unresolved.tail)
                } else if (lOp.forall(Character.isDigit) && resolved.isDefinedAt(rOp)) {
                  resolveAll(resolved + (name -> op.resolve(lOp.toInt, resolved(rOp))), unresolved.tail)
                } else if (rOp.forall(Character.isDigit) && resolved.isDefinedAt(lOp)) {
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp), rOp.toInt)), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
              case op@LShift(lOp, rOp) =>
                if (resolved.isDefinedAt(lOp)) {
                  //                  println(s"Resolved LSHIFT for keys $lOp LSHIFT $rOp with value ${op.resolve(resolved(lOp))}")
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp))), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
              case op@RShift(lOp, rOp) =>
                if (resolved.isDefinedAt(lOp)) {
                  //                  println(s"Resolved RSHIFT for keys $lOp RSHIFT $rOp with value ${op.resolve(resolved(lOp))}")
                  resolveAll(resolved + (name -> op.resolve(resolved(lOp))), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
              case op@Not(rOp) =>
                if (resolved.isDefinedAt(rOp)) {
                  //                  println(s"Resolved NOT for key NOT $rOp with value ${op.resolve(resolved(rOp))}")
                  resolveAll(resolved + (name -> op.resolve(resolved(rOp))), unresolved.tail)
                } else {
                  resolveAll(resolved, unresolved.tail :+ Gate(name, op))
                }
            }
          case None => resolved
        }
      }

      resolveAll(Map[String, Int]("b" -> 16076), parse(lst).filterNot(_.name == "b"))
    }

    def parseLine(s: String): Option[Gate] = s match {
      case rInput(input: String, g: String) => Some(Gate(g, Input(input.toInt)))
      case rDualOperation(lOp: String, op: String, rOp: String, g: String) => op match {
        case "AND" => Some(Gate(g, And(lOp, rOp)))
        case "OR" => Some(Gate(g, Or(lOp, rOp)))
        case "LSHIFT" => Some(Gate(g, LShift(lOp, rOp.toInt)))
        case "RSHIFT" => Some(Gate(g, RShift(lOp, rOp.toInt)))
      }
      case rNotOperation(rOp: String, g: String) => Some(Gate(g, Not(rOp)))
      case rConnection(gateName: String, g: String) => Some(Gate(g, Connection(gateName)))
      case _ => None
    }

    def parse(lst: List[String]) = lst.flatMap(parseLine)
  }

}
