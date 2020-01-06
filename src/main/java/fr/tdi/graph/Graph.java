package fr.tdi.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Generic graph class
 *
 * @param <T>
 */
public class Graph<T> {

  HashMap<T, ArrayList<Pair<T, Float>>> adjacentMap = new HashMap<>();
  GraphType graphType;
  int edgeCount = 0;

  /** Default constructor. Graph type is undirected */
  public Graph() {
    graphType = GraphType.Undirected;
  }

  /**
   * Constructor that can specifies graph type
   *
   * @param graphType
   */
  public Graph(GraphType graphType) {
    this.graphType = graphType;
  }

  /**
   * Add a vertex to graph If vertex does not exist, nothing is done
   *
   * @param vertex
   */
  public void addVertex(T vertex) {
    if (!adjacentMap.containsKey(vertex)) {
      adjacentMap.put(vertex, new ArrayList<Pair<T, Float>>());
    }
  }

  /**
   * Remove a vertex If vertex does not exists, nothing is done If vertex is present, the vertex is
   * removed, and all edges associated are removed
   *
   * @param vertex
   */
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

  /**
   * Check if vertex is in graph
   *
   * @param vertex
   * @return true if exists
   */
  public Boolean exists(T vertex) {
    return adjacentMap.containsKey(vertex);
  }

  /**
   * If vertex does not exists, return an empty list If vertex is present, return a list of <T>
   * which represents the associated vertex to form an edge
   *
   * @param vertex
   * @return List of <T>
   */
  public List<T> getEdges(T vertex) {
    if (adjacentMap.containsKey(vertex)) {
      return adjacentMap.get(vertex).stream().map(e -> e.first).collect(Collectors.toList());
    }

    return new ArrayList<T>(0);
  }

  /**
   * If vertex does not exists, return an empty list If vertex is present, return a list of pair<T,
   * Float> which represents the associated vertex to form an edge with associated weight
   *
   * @param vertex
   * @return List of Pair<T, Float>
   */
  public ArrayList<Pair<T, Float>> getEdgesWithWeight(T vertex) {
    if (adjacentMap.containsKey(vertex)) {
      return adjacentMap.get(vertex);
    } else return new ArrayList<Pair<T, Float>>();
  }

  /**
   * Add an edge with weight 0, starting from vertexA to vertexB
   *
   * @param vertexA
   * @param vertexB
   */
  public void addEdge(T vertexA, T vertexB) {
    addEdge(vertexA, vertexB, 0.f);
  }

  /**
   * Add an edge with weight, starting from vertexA to vertexB If vertexA and vertexB do not exist,
   * they are added If graph type is Undirected, the complementary edge is added automatically
   *
   * @param vertexA
   * @param vertexB
   */
  public void addEdge(T vertexA, T vertexB, Float weight) {

    if (!adjacentMap.containsKey(vertexA)) {
      addVertex(vertexA);
    }

    if (!adjacentMap.containsKey(vertexB)) {
      addVertex(vertexB);
    }

    if (!adjacentMap
        .get(vertexA)
        .stream()
        .filter(e -> e.first == vertexB)
        .findFirst()
        .isPresent()) {
      adjacentMap.get(vertexA).add(new Pair<T, Float>(vertexB, weight));
      edgeCount++;

      if (graphType == GraphType.Undirected) {
        adjacentMap.get(vertexB).add(new Pair<T, Float>(vertexA, weight));
        edgeCount++;
      }
    }
  }

  /**
   * Remove an edge formed by vertexA and vertexB
   *
   * @param vertexA
   * @param vertexB
   */
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

  /** Clear graph */
  public void clear() {
    adjacentMap.clear();
    edgeCount = 0;
  }

  /** @return number of vertices */
  public int getVertexCount() {
    return adjacentMap.size();
  }

  /** @return number of edges */
  public int getEdgeCount() {
    return edgeCount;
  }

  /** @return Graph type */
  public GraphType getGraphType() {
    return graphType;
  }

  /** @return string representation of graph */
  @Override
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
