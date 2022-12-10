name := "aws"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

val sparkVersion = "2.4.3"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "com.typesafe" % "config" % "1.3.3"
)


