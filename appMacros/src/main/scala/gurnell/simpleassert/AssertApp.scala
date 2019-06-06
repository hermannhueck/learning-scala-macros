package gurnell.simpleassert

object AssertApp extends App {

  println

  val a = 1
  val b = 2

  // This simple version of Scala's `assert` macro prints informative
  // error messages only in the case of a failed assertion of the
  // for `a == b`:

  try {
    assert(a == a)
    println("1. assertion successful")
  } catch {
    case exn: AssertionError =>
      println("1. " + exn.getMessage)
  }

  try {
    assert(a.toString == b.toString)
    println("2. assertion successful")
  } catch {
    case exn: AssertionError =>
      println("2. " + exn.getMessage)
  }

  try {
    assert(true)
    println("3. assertion successful")
  } catch {
    case exn: AssertionError =>
      println("3. " + exn.getMessage)
  }

  try {
    assert(false)
    println("4. assertion successful")
  } catch {
    case exn: AssertionError =>
      println("4. " + exn.getMessage)
  }

  println
}
