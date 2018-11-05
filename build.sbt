import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.7",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "scala-rdbms-access",
    libraryDependencies ++= Seq(
      "org.scalikejdbc" %% "scalikejdbc" % "3.3.0",
      "com.h2database" % "h2" % "1.4.197",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.jooq" % "jooq" % "3.11.5",
      "org.jooq" % "jooq-codegen-maven" % "3.11.5",
      "org.jooq" % "jooq-meta" % "3.11.5",
      "com.zaxxer" % "HikariCP" % "3.1.0"
    )
  )
