import sbt.Keys.scalaVersion

name := "spark_cassandra"

version := "1.0"

scalaVersion := "2.12.2"

lazy val Versions = new {
    val phantom = "2.12.1"
    val util = "0.30.1"
    val scalatest = "3.0.1"
    val sclaLang = "2.12.2"
}

libraryDependencies ++=Seq(
    "com.outworkers"   %%  "phantom-dsl"       % Versions.phantom,
    "com.outworkers"   %%  "phantom-streams"   % Versions.phantom,
    "com.outworkers"   %%  "util-testing"      % Versions.util % Test,
    "org.scalatest"    %%  "scalatest"         % Versions.scalatest % Test,
    "org.scala-lang" % "scala-reflect" % "2.12.2"
)

