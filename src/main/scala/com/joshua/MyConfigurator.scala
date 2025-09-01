package com.joshua

import caos.frontend.Configurator.*
import caos.frontend.{Configurator, Documentation}
import caos.sos.SOS
import caos.view.*
import org.scalajs.dom

object MyConfigurator extends Configurator[SimpleGraph]:
  val name = "MyCaosTeste"
  override val languageName = "Graph Demo"

  val graphLinePattern = raw"^\s*(.+?)\s*--(.+?)-->\s*(.+?)\s*$$".r

  val parser: String => SimpleGraph = input => {
    val transitions = collection.mutable.Map.empty[GraphNode, Set[(GraphAction, GraphNode)]]
    input.split('\n').filter(_.nonEmpty).foreach { line =>
      line match {
        case graphLinePattern(source, label, target) =>
          val trimmedSource = source.trim
          val trimmedLabel = label.trim
          val trimmedTarget = target.trim
          transitions.update(trimmedSource, transitions.getOrElse(trimmedSource, Set.empty) + ((trimmedLabel, trimmedTarget)))
        case _ =>
          println(s"Aviso: Linha malformada na definição do grafo (formato: Source --Label--> Target): $line")
      }
    }
    val graph = SimpleGraph(transitions.toMap)
    SimpleGraphSOS.graphInstance = graph
    graph
  }

  val examples = List(
    "Exemplo Vazio" -> "",
    "Grafo Simples" ->
      """A --a--> B
        |B --b--> C
        |A --c--> C
        |C --d--> D""".stripMargin,
    "Grafo com Ciclo" ->
      """Start --go--> State1
        |State1 --next--> State2
        |State2 --back--> State1
        |State1 --end--> Finish""".stripMargin,
    "Grafo com Loop" ->
      """X --loop--> X
        |X --out--> Y""".stripMargin
  )

  val widgets = List(
    "Hello" -> view(_ => "CAOS ligado ao teu projeto ✅", Text),
    "Mini Grafo de Teste (Mermaid)" -> lts(
      initialSt = graph => graph.transitions.keys.headOption.getOrElse(""),
      sos = SimpleGraphSOS,
      viewSt = node => node,
      viewAct = label => label
    ),
    "Mini Grafo de Teste (Cytoscape.js)" -> jsGraph[SimpleGraph, SimpleGraph](
      pre = identity, // Simplesmente passa o SimpleGraph como dado
      renderFunc = CytoscapeRenderer.render // Usa a função de renderização específica
    )
  )