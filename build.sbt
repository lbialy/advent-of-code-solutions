name := "Advent of Code Tasks"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

)

mainClass in (Compile, run) := Some("eu.keios.AdventOfCode.Main")