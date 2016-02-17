
name := "longest-palindrome"

version := "0.1"
     
scalaVersion := "2.11.7"

javacOptions ++= Seq("-target", "1.8")

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  Seq(
    "org.specs2" %% "specs2-core" % "3.7" % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}

resolvers ++= Seq(
  Classpaths.sbtPluginReleases
)
