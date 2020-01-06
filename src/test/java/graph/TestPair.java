package graph;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import fr.tdi.graph.Pair;

public class TestPair {

  @Test
  void pairShouldNotBeNull() {
    Pair<String, Integer> pair = new Pair<>("test", 0);
    assertTrue(pair.getFirst().equals("test"));
    assertTrue(pair.getSecond().equals(0));

    pair.setFirst("test2");
    assertTrue(pair.getFirst().equals("test2"));

    pair.setSecond(10);
    assertTrue(pair.getSecond().equals(10));
  }
}
