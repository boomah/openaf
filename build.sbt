import org.scalajs.sbtplugin.cross.CrossType

lazy val commonSettings = Seq(
  scalaVersion := "2.12.1"
)

lazy val openaf = project.in(file(".")).
  aggregate(server, client).
  settings(
    name := "openaf"
  )

lazy val server = project.in(file("server")).
  settings(
    commonSettings,
    fork in run := true,
    javaOptions in run ++= Seq("-Xms4g", "-Xmx4g", "-server"),
    mainClass in (Compile, run) := Some("openaf.server.Server"),
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "com.typesafe.akka" %% "akka-http" % "10.0.5"
    )
  ).
  dependsOn(apiJvm)

lazy val client = project.in(file("client")).
  settings(
    commonSettings
  ).
  enablePlugins(ScalaJSPlugin).
  dependsOn(apiJs)

lazy val api = crossProject.crossType(CrossType.Pure).
  settings(
    commonSettings
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
  )

lazy val apiJvm = api.jvm

lazy val apiJs = api.js