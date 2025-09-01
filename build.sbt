lazy val caos = project.in(file("lib/caos"))
  .enablePlugins(ScalaJSPlugin)
  .settings(scalaVersion := "3.1.1") // alinha com a versão do CAOS

lazy val MyCaosTeste = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "MyCaosTeste",
    scalaVersion := "3.1.1",       // 2.13+ / 3.x
    scalaJSUseMainModuleInitializer := true,
    Compile / mainClass := Some("src.com.joshua"),
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory :=
      baseDirectory.value / "lib" / "caos" / "tool" / "js" / "gen",
    libraryDependencies ++= Seq(
      // as tuas deps…
    )
  )
  .dependsOn(caos)
