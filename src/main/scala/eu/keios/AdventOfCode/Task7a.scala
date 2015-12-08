package eu.keios.AdventOfCode

import scala.annotation.tailrec

//  --- Day 7: Some Assembly Required ---
//
//  This year, Santa brought little Bobby Tables a set of wires and bitwise logic gates!
//  Unfortunately, little Bobby is a little under the recommended age range, and he needs help assembling the circuit.
//
//  Each wire has an identifier (some lowercase letters) and can carry a 16-bit signal (a number from 0 to 65535).
//  A signal is provided to each wire by a gate, another wire, or some specific value.
//  Each wire can only get a signal from one source, but can provide its signal to multiple destinations.
//  A gate provides no signal until all of its inputs have a signal.
//
//  The included instructions booklet describe how to connect the parts together:
//  x AND y -> z means to connect wires x and y to an AND gate, and then connect its output to wire z.
//
//  For example:
//
//  123 -> x means that the signal 123 is provided to wire x.
//  x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
//  p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
//  NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
//  Other possible gates include OR (bitwise OR) and RSHIFT (right-shift).
//  If, for some reason, you'd like to emulate the circuit instead,
//  almost all programming languages (for example, C, JavaScript, or Python) provide operators for these gates.
//
//  For example, here is a simple circuit:
//
//  123 -> x
//  456 -> y
//  x AND y -> d
//  x OR y -> e
//  x LSHIFT 2 -> f
//  y RSHIFT 2 -> g
//  NOT x -> h
//  NOT y -> i
//  After it is run, these are the signals on the wires:
//
//  d: 72
//  e: 507
//  f: 492
//  g: 114
//  h: 65412
//  i: 65079
//  x: 123
//  y: 456
//  In little Bobby's kit's instructions booklet (provided as your puzzle input),
//  what signal is ultimately provided to wire a?

object Task7a extends Task7a {
  def result = Circuit.run(testInput)("a")
}

trait Task7a extends AdventOfCodeTask {

  val rInput = """(\d+) -> ([a-z]*)""".r
  val rConnection = """(.*) -> ([a-z]*)""".r
  val rDualOperation = """(.*) (AND|OR|LSHIFT|RSHIFT) (.*) -> ([a-z]*)""".r
  val rNotOperation = """NOT (.*) -> ([a-z]*)""".r

  def testInput = "lf AND lq -> ls\niu RSHIFT 1 -> jn\nbo OR bu -> bv\ngj RSHIFT 1 -> hc\net RSHIFT 2 -> eu\nbv AND bx -> by\nis OR it -> iu\nb OR n -> o\ngf OR ge -> gg\nNOT kt -> ku\nea AND eb -> ed\nkl OR kr -> ks\nhi AND hk -> hl\nau AND av -> ax\nlf RSHIFT 2 -> lg\ndd RSHIFT 3 -> df\neu AND fa -> fc\ndf AND dg -> di\nip LSHIFT 15 -> it\nNOT el -> em\net OR fe -> ff\nfj LSHIFT 15 -> fn\nt OR s -> u\nly OR lz -> ma\nko AND kq -> kr\nNOT fx -> fy\net RSHIFT 1 -> fm\neu OR fa -> fb\ndd RSHIFT 2 -> de\nNOT go -> gp\nkb AND kd -> ke\nhg OR hh -> hi\njm LSHIFT 1 -> kg\nNOT cn -> co\njp RSHIFT 2 -> jq\njp RSHIFT 5 -> js\n1 AND io -> ip\neo LSHIFT 15 -> es\n1 AND jj -> jk\ng AND i -> j\nci RSHIFT 3 -> ck\ngn AND gp -> gq\nfs AND fu -> fv\nlj AND ll -> lm\njk LSHIFT 15 -> jo\niu RSHIFT 3 -> iw\nNOT ii -> ij\n1 AND cc -> cd\nbn RSHIFT 3 -> bp\nNOT gw -> gx\nNOT ft -> fu\njn OR jo -> jp\niv OR jb -> jc\nhv OR hu -> hw\n19138 -> b\ngj RSHIFT 5 -> gm\nhq AND hs -> ht\ndy RSHIFT 1 -> er\nao OR an -> ap\nld OR le -> lf\nbk LSHIFT 1 -> ce\nbz AND cb -> cc\nbi LSHIFT 15 -> bm\nil AND in -> io\naf AND ah -> ai\nas RSHIFT 1 -> bl\nlf RSHIFT 3 -> lh\ner OR es -> et\nNOT ax -> ay\nci RSHIFT 1 -> db\net AND fe -> fg\nlg OR lm -> ln\nk AND m -> n\nhz RSHIFT 2 -> ia\nkh LSHIFT 1 -> lb\nNOT ey -> ez\nNOT di -> dj\ndz OR ef -> eg\nlx -> a\nNOT iz -> ja\ngz LSHIFT 15 -> hd\nce OR cd -> cf\nfq AND fr -> ft\nat AND az -> bb\nha OR gz -> hb\nfp AND fv -> fx\nNOT gb -> gc\nia AND ig -> ii\ngl OR gm -> gn\n0 -> c\nNOT ca -> cb\nbn RSHIFT 1 -> cg\nc LSHIFT 1 -> t\niw OR ix -> iy\nkg OR kf -> kh\ndy OR ej -> ek\nkm AND kn -> kp\nNOT fc -> fd\nhz RSHIFT 3 -> ib\nNOT dq -> dr\nNOT fg -> fh\ndy RSHIFT 2 -> dz\nkk RSHIFT 2 -> kl\n1 AND fi -> fj\nNOT hr -> hs\njp RSHIFT 1 -> ki\nbl OR bm -> bn\n1 AND gy -> gz\ngr AND gt -> gu\ndb OR dc -> dd\nde OR dk -> dl\nas RSHIFT 5 -> av\nlf RSHIFT 5 -> li\nhm AND ho -> hp\ncg OR ch -> ci\ngj AND gu -> gw\nge LSHIFT 15 -> gi\ne OR f -> g\nfp OR fv -> fw\nfb AND fd -> fe\ncd LSHIFT 15 -> ch\nb RSHIFT 1 -> v\nat OR az -> ba\nbn RSHIFT 2 -> bo\nlh AND li -> lk\ndl AND dn -> do\neg AND ei -> ej\nex AND ez -> fa\nNOT kp -> kq\nNOT lk -> ll\nx AND ai -> ak\njp OR ka -> kb\nNOT jd -> je\niy AND ja -> jb\njp RSHIFT 3 -> jr\nfo OR fz -> ga\ndf OR dg -> dh\ngj RSHIFT 2 -> gk\ngj OR gu -> gv\nNOT jh -> ji\nap LSHIFT 1 -> bj\nNOT ls -> lt\nir LSHIFT 1 -> jl\nbn AND by -> ca\nlv LSHIFT 15 -> lz\nba AND bc -> bd\ncy LSHIFT 15 -> dc\nln AND lp -> lq\nx RSHIFT 1 -> aq\ngk OR gq -> gr\nNOT kx -> ky\njg AND ji -> jj\nbn OR by -> bz\nfl LSHIFT 1 -> gf\nbp OR bq -> br\nhe OR hp -> hq\net RSHIFT 5 -> ew\niu RSHIFT 2 -> iv\ngl AND gm -> go\nx OR ai -> aj\nhc OR hd -> he\nlg AND lm -> lo\nlh OR li -> lj\nda LSHIFT 1 -> du\nfo RSHIFT 2 -> fp\ngk AND gq -> gs\nbj OR bi -> bk\nlf OR lq -> lr\ncj AND cp -> cr\nhu LSHIFT 15 -> hy\n1 AND bh -> bi\nfo RSHIFT 3 -> fq\nNOT lo -> lp\nhw LSHIFT 1 -> iq\ndd RSHIFT 1 -> dw\ndt LSHIFT 15 -> dx\ndy AND ej -> el\nan LSHIFT 15 -> ar\naq OR ar -> as\n1 AND r -> s\nfw AND fy -> fz\nNOT im -> in\net RSHIFT 3 -> ev\n1 AND ds -> dt\nec AND ee -> ef\nNOT ak -> al\njl OR jk -> jm\n1 AND en -> eo\nlb OR la -> lc\niu AND jf -> jh\niu RSHIFT 5 -> ix\nbo AND bu -> bw\ncz OR cy -> da\niv AND jb -> jd\niw AND ix -> iz\nlf RSHIFT 1 -> ly\niu OR jf -> jg\nNOT dm -> dn\nlw OR lv -> lx\ngg LSHIFT 1 -> ha\nlr AND lt -> lu\nfm OR fn -> fo\nhe RSHIFT 3 -> hg\naj AND al -> am\n1 AND kz -> la\ndy RSHIFT 5 -> eb\njc AND je -> jf\ncm AND co -> cp\ngv AND gx -> gy\nev OR ew -> ex\njp AND ka -> kc\nfk OR fj -> fl\ndy RSHIFT 3 -> ea\nNOT bs -> bt\nNOT ag -> ah\ndz AND ef -> eh\ncf LSHIFT 1 -> cz\nNOT cv -> cw\n1 AND cx -> cy\nde AND dk -> dm\nck AND cl -> cn\nx RSHIFT 5 -> aa\ndv LSHIFT 1 -> ep\nhe RSHIFT 2 -> hf\nNOT bw -> bx\nck OR cl -> cm\nbp AND bq -> bs\nas OR bd -> be\nhe AND hp -> hr\nev AND ew -> ey\n1 AND lu -> lv\nkk RSHIFT 3 -> km\nb AND n -> p\nNOT kc -> kd\nlc LSHIFT 1 -> lw\nkm OR kn -> ko\nid AND if -> ig\nih AND ij -> ik\njr AND js -> ju\nci RSHIFT 5 -> cl\nhz RSHIFT 1 -> is\n1 AND ke -> kf\nNOT gs -> gt\naw AND ay -> az\nx RSHIFT 2 -> y\nab AND ad -> ae\nff AND fh -> fi\nci AND ct -> cv\neq LSHIFT 1 -> fk\ngj RSHIFT 3 -> gl\nu LSHIFT 1 -> ao\nNOT bb -> bc\nNOT hj -> hk\nkw AND ky -> kz\nas AND bd -> bf\ndw OR dx -> dy\nbr AND bt -> bu\nkk AND kv -> kx\nep OR eo -> eq\nhe RSHIFT 1 -> hx\nki OR kj -> kk\nNOT ju -> jv\nek AND em -> en\nkk RSHIFT 5 -> kn\nNOT eh -> ei\nhx OR hy -> hz\nea OR eb -> ec\ns LSHIFT 15 -> w\nfo RSHIFT 1 -> gh\nkk OR kv -> kw\nbn RSHIFT 5 -> bq\nNOT ed -> ee\n1 AND ht -> hu\ncu AND cw -> cx\nb RSHIFT 5 -> f\nkl AND kr -> kt\niq OR ip -> ir\nci RSHIFT 2 -> cj\ncj OR cp -> cq\no AND q -> r\ndd RSHIFT 5 -> dg\nb RSHIFT 2 -> d\nks AND ku -> kv\nb RSHIFT 3 -> e\nd OR j -> k\nNOT p -> q\nNOT cr -> cs\ndu OR dt -> dv\nkf LSHIFT 15 -> kj\nNOT ac -> ad\nfo RSHIFT 5 -> fr\nhz OR ik -> il\njx AND jz -> ka\ngh OR gi -> gj\nkk RSHIFT 1 -> ld\nhz RSHIFT 5 -> ic\nas RSHIFT 2 -> at\nNOT jy -> jz\n1 AND am -> an\nci OR ct -> cu\nhg AND hh -> hj\njq OR jw -> jx\nv OR w -> x\nla LSHIFT 15 -> le\ndh AND dj -> dk\ndp AND dr -> ds\njq AND jw -> jy\nau OR av -> aw\nNOT bf -> bg\nz OR aa -> ab\nga AND gc -> gd\nhz AND ik -> im\njt AND jv -> jw\nz AND aa -> ac\njr OR js -> jt\nhb LSHIFT 1 -> hv\nhf OR hl -> hm\nib OR ic -> id\nfq OR fr -> fs\ncq AND cs -> ct\nia OR ig -> ih\ndd OR do -> dp\nd AND j -> l\nib AND ic -> ie\nas RSHIFT 3 -> au\nbe AND bg -> bh\ndd AND do -> dq\nNOT l -> m\n1 AND gd -> ge\ny AND ae -> ag\nfo AND fz -> gb\nNOT ie -> if\ne AND f -> h\nx RSHIFT 3 -> z\ny OR ae -> af\nhf AND hl -> hn\nNOT h -> i\nNOT hn -> ho\nhe RSHIFT 5 -> hh".split("\n").toList

  object Circuit {

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

      resolveAll(Map.empty[String, Int], parse(lst))
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

  case class Gate(name: String, value: Op)

  sealed trait Op

  case class Connection(value: String) extends Op

  case class Input(value: Int) extends Op

  case class And(lOp: String, rOp: String) extends Op {
    def resolve(l: Int, r: Int) = l & r
  }

  case class Or(lOp: String, rOp: String) extends Op {
    def resolve(l: Int, r: Int) = l | r
  }

  case class LShift(lOp: String, rOp: Int) extends Op {
    def resolve(l: Int) = l << rOp
  }

  case class RShift(lOp: String, rOp: Int) extends Op {
    def resolve(l: Int) = l >> rOp
  }

  case class Not(rOp: String) extends Op {
    def resolve(r: Int) = (~r) & 0xFFFF
  }

}
