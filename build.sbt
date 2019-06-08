
val projectName = "learning-scala-macros"
val githubId = "hermannhueck"
val githubHome = s"https://github.com/$githubId"
val projectUrl = s"$githubHome/$projectName"

inThisBuild(
  Seq(
    organization := "io.hueck",
    organizationName := "Hueck",
    description := "This repo contains the code I copied/modified/produced while learning Scala Macros",
    homepage := Some(url(projectUrl)),
    startYear := Some(2019),
    licenses := Vector(("MIT", url("https://opensource.org/licenses/MIT"))),
    scmInfo := Some(ScmInfo(url(projectUrl), s"$projectUrl.git")),
    developers := List(
      Developer(id = githubId, name = "Hermann Hueck", email = "", url = url(githubHome))
    ),

    version := "0.1.0",

    scalaVersion := "2.13.0-RC3",

    scalacOptions ++= Seq(
      "-encoding", "UTF-8", // source files are in UTF-8
      "-deprecation", // warn about use of deprecated APIs
      "-unchecked", // warn about unchecked type parameters
      "-feature", // warn about misused language features
      //"-language:higherKinds",  // suppress warnings when using higher kinded types
      //"-Ypartial-unification",  // (removed in scala 2.13) allow the compiler to unify type constructors of different arities
      //"-Xlint",                 // enable handy linter warnings
      //"-Xfatal-warnings",       // turn compiler warnings into errors
    ),
  )
)

lazy val root = (project in file("."))
  .aggregate(appMacros, libMacros)
  .settings(
    name := projectName,
  )

lazy val appMacros = (project in file("appMacros"))
  .dependsOn(libMacros)
  .settings(
    name := "appMacros",
    description := "macro applications",
  )

lazy val libMacros = (project in file("libMacros"))
  .settings(
    name := "libMacros",
    description := "macro definitions",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value withSources() withJavadoc(),
      "org.scala-lang" % "scala-compiler" % scalaVersion.value withSources() withJavadoc(),
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2" withSources() withJavadoc(),
      "org.specs2" %% "specs2-core" % "4.5.1" % "test"
    ),
  )

