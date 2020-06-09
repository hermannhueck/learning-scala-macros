val projectName = "learning-scala-macros"

inThisBuild(
  Seq(
    version := "0.1.0",
    scalaVersion := "2.13.2",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",        // source files are in UTF-8
      "-deprecation", // warn about use of deprecated APIs
      "-unchecked",   // warn about unchecked type parameters
      "-feature",     // warn about misused language features
      "-Wconf:cat=deprecation:ws,cat=feature:ws,cat=optimizer:ws,any:warning-verbose"
      //"-Xlint",                 // enable handy linter warnings
      //"-Xfatal-warnings",       // turn compiler warnings into errors
    )
  )
)

lazy val root = (project in file("."))
  .aggregate(appMacros, libMacros)
  .settings(
    name := projectName
  )

lazy val appMacros = (project in file("appMacros"))
  .dependsOn(libMacros)
  .settings(
    name := "appMacros",
    description := "macro applications"
  )

lazy val libMacros = (project in file("libMacros"))
  .settings(
    name := "libMacros",
    description := "macro definitions",
    libraryDependencies ++= Seq(
      "org.scala-lang"         % "scala-reflect"             % scalaVersion.value withSources () withJavadoc (),
      "org.scala-lang"         % "scala-compiler"            % scalaVersion.value withSources () withJavadoc (),
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2" withSources () withJavadoc (),
      "org.specs2"             %% "specs2-core"              % "4.7.0" % "test"
    )
  )
