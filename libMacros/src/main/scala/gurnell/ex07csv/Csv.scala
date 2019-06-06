package gurnell.ex07csv

import scala.language.experimental.macros

object Csv extends CsvImplicits {

  def writeCsv[A: CsvFormat](values: Iterable[A]): String = {
    values.map(implicitly[CsvFormat[A]]).map(_.mkString(",")).mkString("\n")
  }
}
