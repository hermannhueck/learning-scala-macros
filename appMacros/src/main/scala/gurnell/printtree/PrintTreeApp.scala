package gurnell.printtree

object PrintTreeApp extends App {
  import PrintTree._

  // This file is left more-or-less untouched following
  // the development of these examples. Each call to
  // `printTree` was useful at some point in development:

  println("\n------------------------------")
  printTree("Literal and Constant - Int") {
    123
  }

  println("------------------------------")
  printTree("Literal and Constant - String") {
    "abc"
  }

  println("------------------------------")
  printTree("Literal and Constant - expression") {
    "abc" + 123
  }

  println("------------------------------")
  printTree("Select, TermName, and TypeName - access a singleton method") {
    math.max(123, 234)
  }

  println("------------------------------")
  val a = 123

  printTree("Select, TermName, and TypeName - access a local field") {
    a
  }

  println("------------------------------")
  printTree("ValDef and DefDef - declare local variables") {
    val a = 123
    var b = 234
    def c = 345
  }

  println("------------------------------")
  printTree("Multiple expressions in a block") {
    123 ; 234
  }

  println("------------------------------")
  printTree("Method invocation") {
    val a = 123
    val b = 234
    a + b
  }

  println("------------------------------")
  printTree("Class definition") {
    class Foo(bar: String, baz: Int)
  }

  println("------------------------------")
  printTree("If expression") {
    val a = 1
    val b = 2

    if(a > b) a else b
  }

  println("------------------------------")
  printTree("Declarations") {
    def add1(a: Int) = a + 1
  }

  println("------------------------------")
  printTree("Assertion example 1") {
    object Foo {
      val a = 1
      def b = 2
      assert(a == b)
    }
  }

  println("------------------------------")
  printTree("Assertion example 2") {
    def myMethod = {
      val a = 1
      def b = 2
      assert(a == b)
    }
  }

  println("------------------------------")
  printTree("Assertion example 3") {
    def myMethod(a: Int, b: Int) = {
      assert(a == b)
    }
  }
}

object Sum {
  def apply(a: Int, b: Int) = a + b
}
