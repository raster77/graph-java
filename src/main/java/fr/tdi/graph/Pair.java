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
  public T getFirst() {
    return first;
  }

  /**
   * Set value of first element
   *
   * @param value
   */
  public void setFirst(T value) {
    first = value;
  }

  /**
   * Get second element
   *
   * @return U
   */
  public U getSecond() {
    return second;
  }

  /**
   * Set value of second element
   *
   * @param value
   */
  public void setSecond(U value) {
    second = value;
  }
}
