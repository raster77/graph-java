package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.tdi.graph.Graph;
import fr.tdi.graph.algorithm.Bfs;
import fr.tdi.graph.algorithm.Dfs;

class AlgorithmTest {

  Graph<String> getGraph() {
    Graph<String> g = new Graph<>();
    g.addEdge("A", "B");
    g.addEdge("A", "C");
    g.addEdge("A", "E");
    g.addEdge("B", "D");
    g.addEdge("B", "F");
    g.addEdge("C", "G");
    g.addEdge("E", "F");

    return g;
  }

  @Test
  void dfs() {
    Graph<String> g = getGraph();

    List<String> l = Dfs.execute(g, "A");
    for (String s : l) System.out.print(s + " ");

    List<String> assertList = Arrays.asList(new String[] {"A", "B", "D", "F", "E", "C", "G"});

    assertEquals(assertList, l);
  }

  @Test
  void bfs() {
    Graph<String> g = getGraph();

    List<String> l = Bfs.execute(g, "A");
    for (String s : l) System.out.print(s + " ");

    List<String> assertList = Arrays.asList(new String[] {"A", "B", "C", "E", "D", "F", "G"});

    assertEquals(assertList, l);
  }
}
