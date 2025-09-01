package com.joshua
import caos.frontend.Site.initSite

object Main:
  def main(args: Array[String]): Unit =
    initSite[SimpleGraph](MyConfigurator)