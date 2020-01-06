package graph;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.tdi.graph.Graph;
import fr.tdi.graph.algorithm.Bfs;
import fr.tdi.graph.algorithm.BfsPath;
import fr.tdi.graph.algorithm.Dfs;
import fr.tdi.graph.algorithm.DijkstraPath;

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

  Graph<Integer> getIntegerGraph() {
    Graph<Integer> g = new Graph<>();
    g.addEdge(0, 22);
    g.addEdge(1, 50);
    g.addEdge(1, 13);
    g.addEdge(1, 20);
    g.addEdge(2, 32);
    g.addEdge(2, 43);
    g.addEdge(2, 29);
    g.addEdge(5, 28);
    g.addEdge(5, 2);
    g.addEdge(5, 27);
    g.addEdge(7, 19);
    g.addEdge(7, 1);
    g.addEdge(8, 44);
    g.addEdge(8, 26);
    g.addEdge(8, 47);
    g.addEdge(9, 40);
    g.addEdge(9, 37);
    g.addEdge(10, 46);
    g.addEdge(10, 34);
    g.addEdge(11, 39);
    g.addEdge(11, 17);
    g.addEdge(12, 44);
    g.addEdge(12, 28);
    g.addEdge(13, 40);
    g.addEdge(13, 10);
    g.addEdge(13, 12);
    g.addEdge(18, 7);
    g.addEdge(19, 3);
    g.addEdge(20, 17);
    g.addEdge(22, 39);
    g.addEdge(24, 49);
    g.addEdge(25, 4);
    g.addEdge(26, 16);
    g.addEdge(26, 24);
    g.addEdge(28, 6);
    g.addEdge(28, 29);
    g.addEdge(29, 36);
    g.addEdge(29, 41);
    g.addEdge(31, 32);
    g.addEdge(31, 1);
    g.addEdge(33, 42);
    g.addEdge(33, 46);
    g.addEdge(34, 15);
    g.addEdge(34, 25);
    g.addEdge(35, 44);
    g.addEdge(35, 48);
    g.addEdge(36, 17);
    g.addEdge(36, 50);
    g.addEdge(38, 11);
    g.addEdge(38, 47);
    g.addEdge(39, 43);
    g.addEdge(39, 29);
    g.addEdge(40, 49);
    g.addEdge(40, 14);
    g.addEdge(40, 46);
    g.addEdge(41, 0);
    g.addEdge(41, 35);
    g.addEdge(41, 30);
    g.addEdge(42, 27);
    g.addEdge(43, 34);
    g.addEdge(43, 0);
    g.addEdge(44, 22);
    g.addEdge(44, 0);
    g.addEdge(44, 31);
    g.addEdge(45, 25);
    g.addEdge(45, 27);
    g.addEdge(45, 21);
    g.addEdge(46, 39);
    g.addEdge(46, 19);
    g.addEdge(47, 48);
    g.addEdge(48, 49);
    g.addEdge(48, 42);
    g.addEdge(49, 38);
    g.addEdge(49, 13);
    g.addEdge(49, 35);

    return g;
  }

  @Test
  void dfsTest() {

    Dfs d = new Dfs();
    assertTrue(d != null);

    Graph<String> g = getGraph();

    List<String> l = Dfs.execute(g, "A");
    List<String> assertList = Arrays.asList(new String[] {"A", "B", "D", "F", "E", "C", "G"});

    assertEquals(assertList, l);
  }

  @Test
  void bfsTest() {

    Bfs b = new Bfs();
    assertTrue(b != null);

    Graph<String> g = getGraph();

    List<String> l = Bfs.execute(g, "A");
    List<String> assertList = Arrays.asList(new String[] {"A", "B", "C", "E", "D", "F", "G"});

    assertEquals(assertList, l);
  }

  @Test
  void bfsShouldBeEmpty() {
    Graph<String> g = getGraph();

    List<String> l = Bfs.execute(g, "Z");
    assertTrue(l.isEmpty());
  }

  @Test
  void bfsPathTest() {
    BfsPath b = new BfsPath();
    assertTrue(b != null);
    Graph<Integer> g = getIntegerGraph();
    List<Integer> res = BfsPath.execute(g, 7, 38);
    List<Integer> assertList = Arrays.asList(new Integer[] {7, 1, 13, 49, 38});
    assertEquals(assertList, res);
  }

  @Test
  void bfsPathShouldBeEmpty() {
    Graph<Integer> g = getIntegerGraph();
    List<Integer> res = BfsPath.execute(g, 150, 200);
    assertTrue(res.isEmpty());

    res = BfsPath.execute(g, 7, 200);
    assertTrue(res.isEmpty());

    res = BfsPath.execute(g, 200, 38);
    assertTrue(res.isEmpty());
  }

  @Test
  void testDijkstra() {
    DijkstraPath d = new DijkstraPath();
    assertTrue(d != null);
    Graph<Integer> g = new Graph<>();
    g.addEdge(0, 1, 4.f);
    g.addEdge(0, 7, 8.f);
    g.addEdge(1, 2, 8.f);
    g.addEdge(1, 7, 11.f);
    g.addEdge(2, 3, 7.f);
    g.addEdge(2, 8, 2.f);
    g.addEdge(2, 5, 4.f);
    g.addEdge(3, 4, 9.f);
    g.addEdge(3, 5, 14.f);
    g.addEdge(4, 5, 10.f);
    g.addEdge(5, 6, 2.f);
    g.addEdge(6, 7, 1.f);
    g.addEdge(6, 8, 6.f);
    g.addEdge(7, 8, 7.f);

    List<Integer> res = DijkstraPath.execute(g, 8, 4);
    List<Integer> assertList = Arrays.asList(new Integer[] {8, 2, 5, 4});
    assertEquals(assertList, res);
  }

  @Test
  void testDijkstraShouldBeEmpty() {
    Graph<Integer> g = new Graph<>();
    g.addEdge(0, 1, 4.f);
    g.addEdge(0, 7, 8.f);
    g.addEdge(1, 2, 8.f);
    g.addEdge(1, 7, 11.f);
    g.addEdge(2, 3, 7.f);
    g.addEdge(2, 8, 2.f);
    g.addEdge(2, 5, 4.f);
    g.addEdge(3, 4, 9.f);
    g.addEdge(3, 5, 14.f);
    g.addEdge(4, 5, 10.f);
    g.addEdge(5, 6, 2.f);
    g.addEdge(6, 7, 1.f);
    g.addEdge(6, 8, 6.f);
    g.addEdge(7, 8, 7.f);

    List<Integer> res = DijkstraPath.execute(g, 80, 40);
    assertTrue(res.isEmpty());

    res = DijkstraPath.execute(g, 80, 4);
    assertTrue(res.isEmpty());

    res = DijkstraPath.execute(g, 8, 40);
    assertTrue(res.isEmpty());
  }
}
