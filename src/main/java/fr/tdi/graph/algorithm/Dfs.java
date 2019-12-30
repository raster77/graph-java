package fr.tdi.graph.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import fr.tdi.graph.Graph;

public class Dfs {

  public static <T> List<T> execute(Graph<T> graph, T vertex) {
    List<T> res = new ArrayList<>();
    if (graph.exists(vertex)) {
      Stack<T> stack = new Stack<>();
      HashMap<T, Integer> visitedMap = new HashMap<>();

      {
        visitedMap.put(vertex, 0);
        res.add(vertex);
        List<T> edges = graph.getEdges(vertex);
        Collections.reverse(edges);
        for (T e : edges) {
          stack.add(e);
        }
      }

      while (!stack.isEmpty()) {

        T v = stack.pop();

        if (!visitedMap.containsKey(v)) {
          visitedMap.put(v, 0);
          List<T> edges = graph.getEdges(v);
          Collections.reverse(edges);
          res.add(v);

          for (T e : edges) {
            if (!visitedMap.containsKey(e)) {
              stack.add(e);
            }
          }
        }
      }
    }

    return res;
  }
}
