package com.joshua

import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
@JSGlobal("cytoscape")
private object CytoscapeJs extends js.Object {
  def apply(options: js.Dynamic): js.Dynamic = js.native
}

object CytoscapeRenderer {

  def render(containerElement: dom.html.Div, graph: SimpleGraph, widgetName: String): Unit = {
    val cyElements = graph.toCytoscapeElements()

    containerElement.innerHTML = "" // Limpar o container

    // Configurações e estilos do Cytoscape.js
    val cyOptions = js.Dynamic.literal(
      container = containerElement,
      elements = cyElements,
      layout = js.Dynamic.literal(name = "dagre", fit = true, rankDir = "LR", animate = true),
      style = js.Array[js.Dynamic](
        js.Dynamic.literal(
          selector = "node",
          style = js.Dynamic.literal(
            "background-color" -> "#6c7b8b", // SINTAXE CORRIGIDA: `->`
            "label" -> "data(label)",
            "text-valign" -> "center",
            "color" -> "#fff",
            "padding" -> "10px",
            "text-wrap" -> "wrap",
            "text-max-width" -> "80px"
          )
        ),
        js.Dynamic.literal(
          selector = "edge",
          style = js.Dynamic.literal(
            "width" -> 3, // SINTAXE CORRIGIDA: `->`
            "line-color" -> "#ccc",
            "target-arrow-color" -> "#ccc",
            "target-arrow-shape" -> "triangle",
            "label" -> "data(label)",
            "text-background-opacity" -> 1,
            "text-background-color" -> "#fff",
            "text-background-padding" -> "3px",
            "font-size" -> "10px",
            "color" -> "#555",
            "text-border-color" -> "#ddd",
            "text-border-width" -> 1,
            "text-border-opacity" -> 1,
            "edge-text-rotation" -> "autorotate"
          )
        )
      ),
      boxSelectionEnabled = false,
      autounselectify = true
    )

    val cy = CytoscapeJs(cyOptions)

    cy.on("tap", "node", (evt: js.Dynamic) => {
      val node = evt.target
      println(s"Nó clicado: ${node.id()}")
    })
  }
}