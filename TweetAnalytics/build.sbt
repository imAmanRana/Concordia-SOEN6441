name := """TweetAnalytics"""
organization := "com.concordia.graduates"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.6"
