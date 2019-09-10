package gurnell.ex07csv

object CsvApp extends App {
  import Csv._

  println("─" * 80)

  case class Person(name: String, age: Int, address: Address)
  case class Address(house: Int, street: String)

  val people = List(
    Person("Anne",    35, Address(1, "Old Kent Road")),
    Person("Bob",     45, Address(2, "Whitechapel")),
    Person("Charlie", 20, Address(3, "Mayfair")))

  println(writeCsv(people))

  println("─" * 80)
}