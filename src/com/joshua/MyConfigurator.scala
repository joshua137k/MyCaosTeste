// MyConfigurator.scala
import caos.frontend.Configurator
import caos.frontend.ViewType.*
// importa também o que precisares para SOS/LTS, etc.

case object MyConfigurator extends Configurator[MyAst]:
  val name = "Demo MyLang"
  def languageName: String = "MyLang"
  val parser: String => MyAst = MyParser.parse // o teu parser
  val examples = List(
    "Olá mundo" -> parser("...programa de exemplo...")
  )
  val widgets = List(
    "AST (texto)" -> view(_.toString, Text),
    // outros widgets: Mermaid, steps/lts, compare, etc.
  )
