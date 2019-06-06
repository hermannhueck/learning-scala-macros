package gurnell.ex07csv

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

class CsvMacros(val c: blackbox.Context) {

  import c.universe._

  def csvFormatMacro[A: c.WeakTypeTag]: c.Tree = {
    
    val tpe = c.weakTypeOf[A]

    val subseqs = tpe.decls collect {
      case method: MethodSymbol if method.isCaseAccessor =>
        q"implicitly[CsvFormat[${method.returnType}]].apply(value.${method.name})"
    }

    val appended =
      subseqs.reduceLeft((a, b) => q"$a ++ $b")

    q"""
    new CsvFormat[$tpe] {
      def apply(value: $tpe) = $appended
    }
    """
  }
}
