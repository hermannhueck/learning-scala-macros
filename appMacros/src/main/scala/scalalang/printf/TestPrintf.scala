package scalalang.printf

import scala.language.experimental.macros

object TestPrintf extends App {

  import Macros._

  println
  printf("hello %s!\n", "world")
  println
}
