package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.tdi.graph.Graph;
import fr.tdi.graph.GraphType;

class GraphOperationsTest {

  private Graph<String> getGraph() {
    Graph<String> g = new Graph<>();
    g.addEdge("A", "B");
    g.addEdge("A", "D");
    g.addEdge("A", "C");
    g.addEdge("B", "E");
    return g;
  }

  @Test
  void clear() {
    Graph<String> g = new Graph<String>(GraphType.Directed);
    g.addEdge("A", "B");
    g.addEdge("A", "D");
    g.addEdge("A", "C");
    g.addEdge("B", "E");
    g.clear();
    assertEquals(0, g.getVertexCount());
    assertEquals(0, g.getEdgeCount());
  }

  @Test
  void addVertex() {
    Graph<Integer> g = new Graph<>();
    g.addVertex(1);
    g.addVertex(2);

    assertEquals(2, g.getVertexCount());
    assertEquals(0, g.getEdgeCount());
  }

  @Test
  void addEdge() {
    Graph<String> g = getGraph();
    assertEquals(5, g.getVertexCount());
    assertEquals(8, g.getEdgeCount());
  }

  @Test
  void removeEdge() {
    Graph<String> g = getGraph();

    g.removeEdge("A", "B");

    assertEquals(5, g.getVertexCount());
    assertEquals(6, g.getEdgeCount());
  }

  @Test
  void removeVertex() {
    Graph<String> g = getGraph();

    g.removeVertex("B");

    assertEquals(4, g.getVertexCount());
    assertEquals(4, g.getEdgeCount());
  }
}
