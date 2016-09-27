import android.Keys._
import android.Dependencies.{LibraryDependency, aar}


// Using Android Plugin
android.Plugin.androidBuild

// SDK version target
platformTarget in Android := "android-21"

dexMaxHeap in Android := "1500m"

run <<= run in Android

name := "DontTouchMeApp"

version := "1.0"

scalaVersion := "2.11.6"
//crossScalaVersions ++= Seq("2.11.0", "2.11.6")

resolvers += "jcenter" at "http://jcenter.bintray.com"
resolvers += "MPAndroidChart" at "https://jitpack.io"
resolvers += "mvessel" at "https://oss.sonatype.org/content/repositories/snapshots/"


javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

libraryDependencies ++= Seq(
  "com.fortysevendeg" %% "mvessel-android" % "0.2-SNAPSHOT",
  aar("org.macroid" %% "macroid" % "2.0.0-M4"),
  aar("com.android.support" % "support-v4" % "21.0.0"),
  aar("com.github.PhilJay" % "MPAndroidChart" % "v2.2.3"),
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)


addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.10")

scalacOptions in (Compile, compile) ++=
  (dependencyClasspath in Compile).value.files.map("-P:wartremover:cp:" + _.toURI.toURL)

scalacOptions in (Compile, compile) ++= Seq(
  "-P:wartremover:traverser:macroid.warts.CheckUi"
)

proguardScala in Android := true


proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic",
  "-keep class scala.concurrent.ExecutionContext",
  "-keep class com.fortysevendeg.mvessel.AndroidDriver",
  "-keep class slick.driver.SQLiteDriver"
)
