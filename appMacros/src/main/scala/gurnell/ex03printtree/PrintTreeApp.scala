package gurnell.ex03printtree

@annotation.nowarn("cat=other-pure-statement")
object PrintTreeApp extends App {
  import PrintTree._

  // This file is left more-or-less untouched following
  // the development of these examples. Each call to
  // `printTree` was useful at some point in development:

  println
  println("─" * 80)
  printTree("Literal and Constant - Int") {
    123
  }

  println("─" * 80)
  printTree("Literal and Constant - String") {
    "abc"
  }

  println("─" * 80)
  printTree("Literal and Constant - expression") {
    "abc" + 123
  }

  println("─" * 80)
  printTree("Select, TermName, and TypeName - access a singleton method") {
    math.max(123, 234)
  }

  println("─" * 80)
  val a = 123

  printTree("Select, TermName, and TypeName - access a local field") {
    a
  }

  println("─" * 80)
  printTree("ValDef and DefDef - declare local variables") {
    val a = 123
    var b = 234
    def c = 345
  }

  println("─" * 80)
  printTree("Multiple expressions in a block") {
    123; 234
  }

  println("─" * 80)
  printTree("Method invocation") {
    val a = 123
    val b = 234
    a + b
  }

  println("─" * 80)
  printTree("Class definition") {
    class Foo(bar: String, baz: Int)
  }

  println("─" * 80)
  printTree("If expression") {
    val a = 1
    val b = 2

    if (a > b) a else b
  }

  println("─" * 80)
  printTree("Declarations") {
    def add1(a: Int) = a + 1
  }

  println("─" * 80)
  printTree("Assertion example 1") {
    object Foo {
      val a = 1
      def b = 2
      assert(a == b)
    }
  }

  println("─" * 80)
  printTree("Assertion example 2") {
    def myMethod = {
      val a = 1
      def b = 2
      assert(a == b)
    }
  }

  println("─" * 80)
  printTree("Assertion example 3") {
    def myMethod(a: Int, b: Int) = {
      assert(a == b)
    }
  }

  println("─" * 80)
}

object Sum {
  def apply(a: Int, b: Int) = a + b
}
