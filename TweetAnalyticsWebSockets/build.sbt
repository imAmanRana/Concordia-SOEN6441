name := """TweetAnalyticsWebSockets"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.6"

libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.3" % Test

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test
