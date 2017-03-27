name := "read-me-later"

version := "1.0"

scalaVersion := "2.12.1"

val akkaHttpVersion = "10.0.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka"           %% "akka-http-core"     % akkaHttpVersion,
  "com.typesafe.akka"           %% "akka-http"          % akkaHttpVersion,
  "com.typesafe.scala-logging"  %% "scala-logging"      % "3.5.0",
  "ch.qos.logback"              %  "logback-classic"    % "1.1.7",
  "jp.co.bizreach"              %% "aws-dynamodb-scala" % "0.0.5",
  "com.typesafe.akka"           %% "akka-http-testkit"  % akkaHttpVersion % Test
)
