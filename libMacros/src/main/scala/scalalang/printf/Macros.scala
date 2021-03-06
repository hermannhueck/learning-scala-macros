package scalalang.printf

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object Macros {

  def printf(format: String, params: Any*): Unit = macro printf_impl

  def printf_impl(c: blackbox.Context)(format: c.Expr[String], params: c.Expr[Any]*): c.Expr[Unit] = {

    import c.universe._

    val Literal(Constant(s_format: String)) = format.tree

    val evals: ListBuffer[ValDef] = ListBuffer[ValDef]()

    def precompute(value: Tree, tpe: Type): Ident = {
      val freshName = TermName(c.freshName("eval$"))
      evals += ValDef(Modifiers(), freshName, TypeTree(tpe), value)
      Ident(freshName)
    }

    val paramsStack: mutable.Stack[Tree] = mutable.Stack[Tree](params map (_.tree): _*)

    val formatStrings: Array[String] = s_format.split("(?<=%[\\w%])|(?=%[\\w%])")

    val refs: Array[c.universe.Tree] = formatStrings map {
      case "%d" => precompute(paramsStack.pop, typeOf[Int])
      case "%s" => precompute(paramsStack.pop, typeOf[String])
      case "%%" => Literal(Constant("%"))
      case part => Literal(Constant(part))
    }

    val stats: ListBuffer[Tree] = evals ++ refs.map { ref =>
      reify(print(c.Expr[Any](ref).splice)).tree
    }

    c.Expr[Unit](Block(stats.toList, Literal(Constant(()))))
  }
}
