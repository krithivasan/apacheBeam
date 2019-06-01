name := "s3-read-write"

version := "0.1"

scalaVersion := "2.11.8"

val apacheBeamV = "2.12.0"

libraryDependencies ++=
  Seq("org.apache.beam" % "beam-sdks-java-core" % apacheBeamV,
    "org.apache.beam" % "beam-runners-direct-java" % apacheBeamV % "runtime",
    "org.apache.beam" % "beam-runners-spark" % apacheBeamV,
    "org.apache.beam" % "beam-sdks-java-io-amazon-web-services" % apacheBeamV
  )

