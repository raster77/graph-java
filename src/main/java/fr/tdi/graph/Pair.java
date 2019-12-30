package fr.tdi.graph;

public class Pair<T, U> {

  T first;
  U second;

  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }

  T getFirst() {
    return first;
  }

  U getSecond() {
    return second;
  }
}
