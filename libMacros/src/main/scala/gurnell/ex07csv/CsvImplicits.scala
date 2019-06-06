package gurnell.ex07csv

import scala.language.experimental.macros

trait LowPriorityCsvImplicits {

  implicit def csvFormat[A]: CsvFormat[A] =
    macro CsvMacros.csvFormatMacro[A]
}

trait CsvImplicits extends LowPriorityCsvImplicits {

  def apply[A](func: A => Seq[String]): CsvFormat[A] = new CsvFormat[A] {
    def apply(value: A): Seq[String] = func(value)
  }

  implicit val stringFormat: CsvFormat[String] = apply[String] { value =>
    Seq(value)
  }

  implicit val intFormat: CsvFormat[Int] = apply[Int] { value =>
    Seq(value.toString)
  }
}
