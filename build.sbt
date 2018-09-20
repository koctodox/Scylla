import sbt.Keys.{mainClass, organization, version}

val export = """^([^=]+)="?(.*?)"?$""".r

lazy val commonSettings = Seq(
  organization := "com.scylla",
  scalaVersion := "2.12.6"
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    name := "Scylla",
    version := {
      scala.io.Source.fromFile(file(".env"))
        .getLines().map(_.trim).flatMap {
        case export(key, value) =>
          Some((key, value))
        case _ => None
      }.toMap.get("VERSION").get.toString
    },
    libraryDependencies ++= {
      Seq(
        "com.typesafe.akka" %% "akka-actor" % "2.5.16",
        "com.typesafe.akka" %% "akka-http" % "10.1.5",
        "com.typesafe.akka" %% "akka-stream" % "2.5.16",
        "ch.megard" %% "akka-http-cors" % "0.3.0",
        "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
        "com.h2database" % "h2" % "1.4.191",
        "ch.qos.logback" % "logback-classic" % "1.2.3",
        "com.typesafe.slick" %% "slick" % "3.2.3",
        "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
        "org.postgresql" % "postgresql" % "9.4-1204-jdbc42",
        "com.typesafe" % "config" % "1.3.1",
        "joda-time" % "joda-time" % "2.7",
        "org.joda" % "joda-convert" % "1.7",
        "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
        "com.pauldijou" %% "jwt-core" % "0.18.0"
      )
    }
  )
  .enablePlugins(JavaAppPackaging)
