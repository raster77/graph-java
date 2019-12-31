/** */
package fr.tdi.graph.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import fr.tdi.graph.Graph;
import fr.tdi.graph.Pair;

/** Return the shortest path from a graph using Dijkstra algorithm */
public class DijkstraPath {

  /**
   * Performs a Dijkstra search and return the shortest path
   *
   * @param <T>
   * @param graph
   * @param vertexA
   * @param vertexB
   * @return List<T>
   */
  public static <T> List<T> execute(Graph<T> graph, T vertexA, T vertexB) {

    class VertexComparator implements Comparator<Pair<Float, T>> {
      public int compare(Pair<Float, T> a, Pair<Float, T> b) {
        if (a.getFirst() > b.getFirst()) {
          return 1;
        } else if (a.getFirst() < b.getFirst()) {
          return -1;
        }
        return 0;
      }
    }

    List<T> res = new ArrayList<>();
    if (graph.exists(vertexA) && graph.exists(vertexB)) {
      PriorityQueue<Pair<Float, T>> pq = new PriorityQueue<>(new VertexComparator());
      HashMap<T, Pair<Float, T>> pathMap = new HashMap<>();

      pq.add(new Pair<Float, T>(0.f, vertexA));
      pathMap.put(vertexA, new Pair<Float, T>(0.f, vertexA));

      while (!pq.isEmpty()) {
        T v = pq.poll().getSecond();

        if (v == vertexB) {
          break;
        }

        List<Pair<T, Float>> edges = graph.getEdgesWithWeight(v);
        for (Pair<T, Float> e : edges) {

          Float weight = pathMap.get(v).getFirst() + e.getSecond();

          if (!pathMap.containsKey(e.getFirst())) {
            pathMap.put(e.getFirst(), new Pair<Float, T>(weight, v));
            pq.add(new Pair<Float, T>(weight, e.getFirst()));
          } else if (pathMap.get(e.getFirst()).getFirst() > weight) {
            pathMap.get(e.getFirst()).setFirst(weight);
            pq.add(new Pair<Float, T>(weight, e.getFirst()));
          }
        }
      }
      res.add(vertexB);
      T vertex = pathMap.get(vertexB).getSecond();

      while (vertex != vertexA) {
        res.add(vertex);
        vertex = pathMap.get(vertex).getSecond();
      }

      res.add(vertexA);
      Collections.reverse(res);
    }

    return res;
  }
}
