package fr.tdi.graph.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import fr.tdi.graph.Graph;

public class Bfs {

  public static <T> List<T> execute(Graph<T> graph, T vertex) {

    List<T> res = new ArrayList<>();
    if (graph.exists(vertex)) {
      LinkedList<T> queue = new LinkedList<>();
      HashMap<T, Integer> visitedMap = new HashMap<>();

      {
        visitedMap.put(vertex, 0);
        res.add(vertex);
        List<T> edges = graph.getEdges(vertex);
        for (T e : edges) {
          queue.add(e);
        }
      }

      // queue.add(vertex);

      while (!queue.isEmpty()) {

        T v = queue.poll();

        if (!visitedMap.containsKey(v)) {
          visitedMap.put(v, 0);
          List<T> edges = graph.getEdges(v);

          res.add(v);
          for (T e : edges) {
            if (!visitedMap.containsKey(e)) {
              queue.add(e);
            }
          }
        }
      }
    }

    return res;
  }
}
