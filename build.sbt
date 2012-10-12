import ScalaxbKeys._

organization := "com.nem"

name := "scalaxb_executor_client"

seq(scalaxbSettings: _*)

packageName in scalaxb in Compile := "com.nem.web.client.scalaxb"

sourceGenerators in Compile <+= scalaxb in Compile

libraryDependencies += "net.databinder" %% "dispatch-http" % "0.8.8"

resolvers +=  Resolver.sonatypeRepo("releases") 