package gurnell.ex01hello

object HelloMacros extends App {

  println("─" * 80)

  import LibraryMacros._

  // Greeting is a macro that that prints "Hello world!"
  // and the time at the point of running the macro.
  //
  // In other words, it prints the time at the point of compilation:
  println(greeting)

  // Contrast this with HelloWorld.scala that executes at run time.

  println("─" * 80)
}
