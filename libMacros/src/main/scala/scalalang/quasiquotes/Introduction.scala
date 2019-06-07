package scalalang.quasiquotes

import scalalang.quasiquotes.Quasiquotes.universe

import scala.reflect.runtime

object Quasiquotes extends App {

  println

  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe

  import universe._

  import scala.reflect.runtime.currentMirror
  import scala.tools.reflect.ToolBox

  val toolbox: ToolBox[runtime.universe.type] = currentMirror.mkToolBox()

  val C: universe.Tree = q"class C"

  println(showCode(C))
  println

  println(showRaw(C))
  println

  val tree = q"i am { a quasiquote }"
  println(tree match { case q"i am { a quasiquote }" => "it worked!" })
  println

  println(q"foo + bar" equalsStructure q"foo.+(bar)")
  println

  val aquasiquote: universe.Select = q"a quasiquote"
  val tree2 = q"i am { $aquasiquote }"
  println(showCode(tree2))
  println(showRaw(tree2))
  println

  val q"i am $what" = q"i am { a quasiquote }"
  println(showCode(what))
  println(showRaw(what))
  println

  val x: universe.ValDef = q"""
  val x: List[Int] = List(1, 2) match {
    case List(a, b) => List(a + b)
  }
  """
  println(showCode(x))
  println(showRaw(x))
  println

  println(q"List[Int]" equalsStructure tq"List[Int]")
  println(showRaw(q"List[Int]"))
  println(showRaw(tq"List[Int]"))
  println

  println(q"List(a, b)" equalsStructure pq"List(a, b)")
  println(showRaw(q"List(a, b)"))
  println(showRaw(pq"List(a, b)"))
  println

  val ab = List(q"a", q"b")
  println(showRaw(ab))
  val fab = q"f(..$ab)"
  println(showRaw(fab))
  println

  val c = q"c"
  println(showRaw(c))
  val fabc = q"f(..$ab, $c)"
  println(showRaw(fabc))
  val fcab = q"f($c, ..$ab)"
  println(showRaw(fcab))
  val fabcab = q"f(..$ab, $c, ..$ab)"
  println(showRaw(fabcab))
  println

  val argss = List(ab, List(c))
  println(showRaw(argss))
  val fargss = q"f(...$argss)"
  println(showRaw(fargss))
  println

  val q"f(..$argss2)" = q"f(a, b)"
  // argss2: List[universe.Tree] = List(a, b)

  val q"f(...$argss3)" = q"f(a, b)(c)"
  // argss3: List[List[universe.Tree]] = List(List(a, b), List(c))
  println

}
