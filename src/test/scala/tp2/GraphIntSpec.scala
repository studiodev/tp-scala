package tp2

import org.scalatest._
import core.UnitSpec

class GraphIntSpec extends UnitSpec {

  /**
   *    x   x
   *     \ /
   *  x  (3)  x   x
   *   \ /     \ /
   *   (2)     (4)
   *     \__ __/
   *       (1)
   */
  val graph: Graph = Node(1,
    Node(2,
      Leaf,
      Node(3, Leaf, Leaf)
    ),
    Node(4, Leaf, Leaf)
  )

  "GraphInt constructor" - {
    "Allow to create unbalanced binary graph" in {
      graph.toString should === ("1[2[*,3[*,*]],4[*,*]]")
    }
  }

  "GraphInt operations allow to" - {

    "Count the number of leaf" in {
      graph.countLeafs should === (5)
    }

    "Count the number of nodes" in {
      fail
    }

    "Sum the node values" in {
      fail
    }

    "Check if the graph contains an element" in {
      fail
    }

    "Execute a function for each value of the graph" in {
      fail
    }

    "Transform the value of each node with the map function" in {
      fail
    }

    "Cut the graph after the Nth levels (root is level 1)" in {
      val cut = graph.cutAtLevel(2)
      cut should === (Node(1, Node(2, Leaf, Leaf), Node(4, Leaf, Leaf)))
    }

  }
}