package warski.debug

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/*
  http://www.warski.org/blog/2012/12/starting-with-scala-macros-a-short-tutorial/
  https://github.com/adamw/scala-macro-debug
*/
trait DebugMacros {
  def debug(params: Any*): Unit = macro DebugMacros.debug_impl
}

object DebugMacros extends DebugMacros {

  /*
    macro hello
  */
  def hello(): Unit = macro hello_impl

  def hello_impl(c: blackbox.Context)(): c.Expr[Unit] = {
    import c.universe._
    reify { println("Hello World!") }
  }

  /*
    macro printparam
  */
  def printparam(param: Any): Unit = macro printparam_impl

  def printparam_impl(c: blackbox.Context)(param: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._
    reify { println(param.splice) }
  }

  /*
    macro debug1
  */
  def debug1(param: Any): Unit = macro debug1_impl

  def debug1_impl(c: blackbox.Context)(param: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._
    val paramRep = show(param.tree)
    val paramRepTree = Literal(Constant(paramRep))
    val paramRepExpr = c.Expr[String](paramRepTree)
    reify { println(paramRepExpr.splice + " = " + param.splice) }
  }

  /*
    macro debug
  */
  def debug_impl(c: blackbox.Context)(params: c.Expr[Any]*): c.Expr[Unit] = {
    import c.universe._

    val trees = params.map { param =>
      param.tree match {
        // Keeping constants as-is
        // The c.universe prefixes aren't necessary, but otherwise Idea keeps importing weird stuff ...
        case c.universe.Literal(c.universe.Constant(const)) =>
          val reified = reify { print(param.splice) }
          reified.tree

        case _ =>
          val paramRep = show(param.tree)
          val paramRepTree = Literal(Constant(paramRep))
          val paramRepExpr = c.Expr[String](paramRepTree)
          val reified = reify {
            print(paramRepExpr.splice + " = " + param.splice)
          }
          reified.tree
      }
    }

    // Inserting ", " between trees, and a println at the end.
    val separators = (1 until trees.size)
      .map(_ => reify { print(", ") }.tree) :+ reify { println() }.tree
    val treesWithSeparators =
      trees.zip(separators).flatMap(p => List(p._1, p._2))

    c.Expr[Unit](Block(treesWithSeparators.toList, Literal(Constant(()))))
  }
}
