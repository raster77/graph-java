package fr.tdi.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Graph<T> {

  HashMap<T, ArrayList<Pair<T, Float>>> adjacentMap = new HashMap<>();
  GraphType graphType;
  int edgeCount = 0;

  public Graph() {
    graphType = GraphType.Undirected;
  }

  public Graph(GraphType graphType) {
    this.graphType = graphType;
  }

  public void addVertex(T vertex) {
    if (!adjacentMap.containsKey(vertex)) {
      adjacentMap.put(vertex, new ArrayList<Pair<T, Float>>());
    }
  }

  public void removeVertex(T vertex) {
    if (adjacentMap.containsKey(vertex)) {
      List<T> toDelete =
          adjacentMap.get(vertex).stream().map(pair -> pair.first).collect(Collectors.toList());

      toDelete.forEach(
          element -> {
            if (adjacentMap.get(element).removeIf(p -> p.first == vertex)) {
              edgeCount--;
            }
          });

      int size = adjacentMap.get(vertex).size();

      adjacentMap.remove(vertex);

      if (!adjacentMap.containsKey(vertex)) {
        edgeCount -= size;
      }
    }
  }

  public Boolean exists(T vertex) {
    return adjacentMap.containsKey(vertex);
  }

  public List<T> getEdges(T vertex) {
    if (adjacentMap.containsKey(vertex)) {
      return adjacentMap.get(vertex).stream().map(e -> e.first).collect(Collectors.toList());
    }

    return new ArrayList<T>();
  }

  public ArrayList<Pair<T, Float>> getEdgesWithWeight(T vertex) {
    if (adjacentMap.containsKey(vertex)) {
      return adjacentMap.get(vertex);
    }

    return new ArrayList<Pair<T, Float>>();
  }

  public void addEdge(T vertexA, T vertexB) {
    addEdge(vertexA, vertexB, 0.f);
  }

  public void addEdge(T vertexA, T vertexB, Float weight) {

    if (!adjacentMap.containsKey(vertexA)) {
      addVertex(vertexA);
    }

    if (!adjacentMap.containsKey(vertexB)) {
      addVertex(vertexB);
    }

    adjacentMap.get(vertexA).add(new Pair<T, Float>(vertexB, weight));
    edgeCount++;

    if (graphType == GraphType.Undirected) {
      adjacentMap.get(vertexB).add(new Pair<T, Float>(vertexA, weight));
      edgeCount++;
    }
  }

  public void removeEdge(T vertexA, T vertexB) {
    if (adjacentMap.containsKey(vertexA)) {

      if (adjacentMap.get(vertexA).removeIf(p -> p.first == vertexB)) {
        edgeCount--;
      }

      if (graphType == GraphType.Undirected && adjacentMap.containsKey(vertexB)) {
        if (adjacentMap.get(vertexB).removeIf(p -> p.first == vertexA)) {
          edgeCount--;
        }
      }
    }
  }

  public void clear() {
    adjacentMap.clear();
    edgeCount = 0;
  }

  public int getVertexCount() {
    return adjacentMap.size();
  }

  public int getEdgeCount() {
    return edgeCount;
  }

  public String toString() {

    AtomicReference<StringBuilder> res = new AtomicReference<>(new StringBuilder());

    adjacentMap.forEach(
        (K, V) -> {
          res.get().append(K.toString() + ": ");
          V.forEach(
              e -> {
                res.get().append(e.first.toString() + " ");
              });
          res.get().append("\n");
        });
    return res.get().toString();
  }
}
