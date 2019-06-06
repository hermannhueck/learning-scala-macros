package gurnell.ex01hello

import java.util.Date

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object LibraryMacros {

  def greeting: String = macro greetingMacro

  def greetingMacro(c: blackbox.Context): c.Tree = {

    import c.universe._

    val now = new Date().toString

    q"""
     "Hi! This code was compiled at " +
     $now
     """
  }
}
