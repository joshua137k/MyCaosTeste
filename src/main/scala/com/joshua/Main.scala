package com.joshua
import caos.frontend.Site.initSite

object Main:
  def main(args: Array[String]): Unit =
    initSite[Unit](MyConfigurator)  // usa Unit por enquanto
