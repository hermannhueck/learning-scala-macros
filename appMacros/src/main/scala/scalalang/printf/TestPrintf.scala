package scalalang.printf

import scala.language.experimental.macros

object TestPrintf extends App {

  import Macros._

  println("─" * 50)
  printf("hello %s, No. %d!\n", "world", 5 )
  println("─" * 50)
}
