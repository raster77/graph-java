/** */
package fr.tdi.graph.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import fr.tdi.graph.Graph;

/** Return the shortest path from a graph using breadth first search */
public class BfsPath {

  /**
   * Performs a breadth first search and return the shortest path
   *
   * @param <T>
   * @param graph
   * @param vertexA
   * @param vertexB
   * @return List<T>
   */
  public static <T> List<T> execute(Graph<T> graph, T vertexA, T vertexB) {
    List<T> res = new ArrayList<>();
    if (graph.exists(vertexA) && graph.exists(vertexB)) {
      LinkedList<T> queue = new LinkedList<>();
      HashMap<T, Integer> visitedMap = new HashMap<>();
      HashMap<T, T> pathMap = new HashMap<>();

      {
        visitedMap.put(vertexA, 0);
        List<T> edges = graph.getEdges(vertexA);
        Collections.reverse(edges);
        for (T e : edges) {
          queue.add(e);
        }
      }

      Boolean forceExit = false;
      while (!queue.isEmpty()) {

        if (forceExit) {
          break;
        }

        T v = queue.poll();

        if (!visitedMap.containsKey(v)) {
          if (v == vertexB) {
            break;
          }

          visitedMap.put(v, 0);
          List<T> edges = graph.getEdges(v);
          Collections.reverse(edges);
          for (T e : edges) {
            if (!visitedMap.containsKey(e)) {
              queue.add(e);
              pathMap.put(e, v);
              if (e == vertexB) {
                forceExit = true;
                break;
              }
            }
          }
        }
      }

      T current = vertexB;
      while (current != vertexA) {
        res.add(current);
        if (!pathMap.containsKey(current)) {
          break;
        } else {
          current = pathMap.get(current);
        }
      }

      if (res.size() == 1) {
        List<T> edges = graph.getEdges(vertexA);
        if (!edges.contains(vertexB)) {
          res.clear();
        }
      }
      if (!res.isEmpty()) {
        res.add(vertexA);
        Collections.reverse(res);
      }
    }

    return res;
  }
}
