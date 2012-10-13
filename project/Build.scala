import sbt._
import Keys._
import PlayProject._
import sbtbuildinfo.Plugin._
import sbtscalaxb.Plugin._
import sbtscalaxb.Plugin.ScalaxbKeys._

object ApplicationBuild extends Build {

  import Dependencies._

  val appName = "scalaxb_executor_client"
  val appVersion = "0.0.1"

  val appDependencies = compileDeps ++ testDeps

  lazy val ourSettings = Seq[Project.Setting[_]](
    organization := "com.nem",
    version := appVersion,
    scalaVersion := Dependencies.V.thisScala,
    crossScalaVersions := Seq(Dependencies.V.thisScala, Dependencies.V.newerScala),
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers ++= Dependencies.resolutionRepos,
    sourceDirectories in Compile <+= baseDirectory / "test",
    testOptions in Test += 
      Tests.Argument(TestFrameworks.Specs2, "console", "junitxml"),
    logLevel in compile := Level.Error
  )

  lazy val allSettings = Defaults.defaultSettings ++ ourSettings ++ 
    buildInfoSettings ++ ourBuildInfoSettings ++ scalaxbSettings ++
    ourScalaxbSettings

  lazy val ourBuildInfoSettings = Seq(
    sourceGenerators in Compile <+= buildInfo,
    sourceGenerators in Test <+= buildInfo,
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "scalaxb_executor_client"
  )
  lazy val ourScalaxbSettings = Seq(
      sourceGenerators in Compile <+= scalaxb in Compile,
      sourceGenerators in Compile <+= scalaxb in Test,
      packageName in scalaxb in Compile := "com.nem.web.client.scalaxb")

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA,
    settings = allSettings)
}

object Dependencies {
  val resolutionRepos = Seq(
    "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
  )
  val compileDeps = Seq(
    "mysql" % "mysql-connector-java" % V.mysql,
    "org.slf4j" % "slf4j-api" % V.slf4j,
    "ch.qos.logback" % "logback-classic" % V.logback,
    "org.mockito" % "mockito-core" % V.mockito,
    "play" %% "play" % play.core.PlayVersion.current,
    "play" %% "play-test" % play.core.PlayVersion.current,
    "play" %% "anorm" % play.core.PlayVersion.current,
    "play" %% "templates" % play.core.PlayVersion.current,
    "play" %% "play" % play.core.PlayVersion.current % V.sources,
    "play" %% "play-test" % play.core.PlayVersion.current % V.sources,
    "play" %% "anorm" % play.core.PlayVersion.current % V.sources,
    "play" %% "templates" % play.core.PlayVersion.current % V.sources,
    "net.databinder" %% "dispatch-http" % V.scalaxb
  )

  val testDeps = Seq(
    "mysql" % "mysql-connector-java" % V.mysql,
    "org.slf4j" % "slf4j-api" % V.slf4j,
    "ch.qos.logback" % "logback-classic" % V.logback,
    "org.mockito" % "mockito-core" % V.mockito,
    "org.specs2" %% "specs2" % V.specs2,
    "play" %% "play" % play.core.PlayVersion.current,
    "play" %% "play-test" % play.core.PlayVersion.current,
    "play" %% "anorm" % play.core.PlayVersion.current,
    "play" %% "templates" % play.core.PlayVersion.current,
    "play" %% "play" % play.core.PlayVersion.current % V.sources,
    "play" %% "play-test" % play.core.PlayVersion.current % V.sources,
    "play" %% "anorm" % play.core.PlayVersion.current % V.sources,
    "play" %% "templates" % play.core.PlayVersion.current % V.sources,
    "net.databinder" %% "dispatch-http" % V.scalaxb
  )

  object V {
    val mockito = "1.9.0"
    val slf4j = "1.6.4"
    val logback = "1.0.0"
    val specs2 = "1.9"
    val mysql = "5.1.19"
    val scalaxb = "0.8.8"
    //Scala Versions
    val oldScala = "2.9.1"
    val thisScala = "2.9.2"
    val newerScala = "2.9.2"
    val sources = "sources"
  }

  def addScalaV(namespace: String, version: String): String = appendAll(namespace, "_", version)

  def appendAll(strings: String*) = {
    val sb = new StringBuilder()
    strings.foreach(s => sb.append(s))
    sb.toString
  }
}
