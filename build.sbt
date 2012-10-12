import ScalaxbKeys._

organization := "com.nem"

name := "scalaxb_executor_client"

seq(scalaxbSettings: _*)

packageName in scalaxb in Compile := "com.nem.web.client.scalaxb"

sourceGenerators in Compile <+= scalaxb in Compile

libraryDependencies += "net.databinder" %% "dispatch-http" % "0.8.8"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.0"

libraryDependencies += "org.specs2" %% "specs2" % "1.9"

resolvers +=  Resolver.sonatypeRepo("releases") 