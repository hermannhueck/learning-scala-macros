package gurnell.ex07csv

trait CsvFormat[A] extends (A => Seq[String])