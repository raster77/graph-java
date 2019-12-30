package fr.tdi.graph;

/**
 * Immutable Pair. Mimic the C++ std::pair
 *
 * @param <T>
 * @param <U>
 */
public class Pair<T, U> {

  T first;
  U second;

  /**
   * Default constructor
   *
   * @param first First element
   * @param second Second element
   */
  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Get first element
   *
   * @return T
   */
  T getFirst() {
    return first;
  }

  /**
   * Get second element
   *
   * @return U
   */
  U getSecond() {
    return second;
  }
}
