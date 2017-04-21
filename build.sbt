name := "codility"
scalaVersion := "2.12.2"
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"
libraryDependencies += "org.hamcrest" % "hamcrest-library" % "1.3"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
