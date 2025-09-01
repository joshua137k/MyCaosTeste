package com.joshua

import org.scalajs.dom


object MyConfigurator extends caos.frontend.Configurator[Unit]:
  val name         = "MyCaosTeste"
  def languageName = "Demo"
  val parser: String => Unit = _ => ()
  val examples = List("Exemplo vazio" -> "")

  // por enquanto, não vamos usar ViewType — só um widget mínimo:
  val widgets = List(
    "Hello" -> view(_ => {
      val el = dom.document.createElement("div").asInstanceOf[dom.HTMLElement]
      el.textContent = "CAOS ligado ao teu projeto ✅"
      el
    })
  )
