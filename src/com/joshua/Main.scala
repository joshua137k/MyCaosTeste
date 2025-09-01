// Main.scala
package src.com.joshua
import caos.frontend.Site.initSite

object Main:
  def main(args: Array[String]): Unit =
    initSite[MyAst](MyConfigurator) // liga a tua config
