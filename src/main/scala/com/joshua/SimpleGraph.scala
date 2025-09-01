package com.joshua

import caos.sos.SOS
import scala.scalajs.js
import scala.collection.mutable

type GraphNode = String
type GraphAction = String

case class SimpleGraph(transitions: Map[GraphNode, Set[(GraphAction, GraphNode)]]) {

  def toCytoscapeElements(): js.Array[js.Object] = {
    val elements = js.Array[js.Object]()
    val seenNodes = mutable.Set[GraphNode]()
    var edgeCounter = 0

    transitions.foreach { case (sourceNode, outgoing) =>
      if (!seenNodes.contains(sourceNode)) {
        elements.append(js.Dynamic.literal(
          group = "nodes",
          data = js.Dynamic.literal(id = sourceNode, label = sourceNode)
        ).asInstanceOf[js.Object])
        seenNodes.add(sourceNode)
      }

      outgoing.foreach { case (actionLabel, targetNode) =>
        if (!seenNodes.contains(targetNode)) {
          elements.append(js.Dynamic.literal(
            group = "nodes",
            data = js.Dynamic.literal(id = targetNode, label = targetNode)
          ).asInstanceOf[js.Object])
          seenNodes.add(targetNode)
        }

        elements.append(js.Dynamic.literal(
          group = "edges",
          data = js.Dynamic.literal(
            id = s"edge${edgeCounter}",
            source = sourceNode,
            target = targetNode,
            label = actionLabel
          )
        ).asInstanceOf[js.Object])
        edgeCounter += 1
      }
    }
    elements
  }
}

object SimpleGraphSOS extends SOS[GraphAction, GraphNode]:
  var graphInstance: SimpleGraph = SimpleGraph(Map.empty)
  override def next[A >: GraphAction](s: GraphNode): Set[(A, GraphNode)] =
    graphInstance.transitions.getOrElse(s, Set.empty).map {
      case (label, target) => (label, target)
    }.asInstanceOf[Set[(A, GraphNode)]]
  override def accepting(s: GraphNode): Boolean = false