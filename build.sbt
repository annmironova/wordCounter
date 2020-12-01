name := "pr111"

version := "0.1"

scalaVersion := "2.12.7"

val sparkVersion = "3.0.1"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.0.1"
//https://github.com/holdenk/spark-testing-base.git
// libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "3.0.1_0.14.0" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
