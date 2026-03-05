import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PairTest {

  private Pair<String, Integer> pair;

  @BeforeEach
  public void setUp() {
    pair = new Pair<>();
  }

  @Test
  public void testPair() {
    Pair<String, Integer> p = new Pair<>();
  }

  @Test
  public void testAllArgsConstructor() {
    Pair<String, Integer> pair = new Pair<>("first", 2);
  }

  @Test
  public void testPairGetters() {
    Pair<Integer, String> p = new Pair<>(1, "second");
    assertEquals(1, p.getFirst());
    assertEquals("second", p.getSecond());
  }

  @Test
  public void testPairSettersAndGetters() {
    pair.setFirst("first");
    pair.setSecond(2);

    assertEquals("first", pair.getFirst());
    assertEquals(2, pair.getSecond());
  }

  @Test
  public void testPairEquals() {
    Pair<String, Integer> p1 = new Pair<>("first", 2);
    Pair<String, Integer> p2 = new Pair<>("first", 2);

    assertTrue(p1.equals(p2));
  }

  @Test
  public void testPairNotEquals() {
    Pair<String, Integer> p1 = new Pair<>("first", 2);
    Pair<String, Integer> p2 = new Pair<>("second", 2);

    assertFalse(p1.equals(p2));
  }

  @Test
  public void testPairEqualsDifferentClass() {
    Pair<String, Integer> p1 = new Pair<>("first", 2);
    Integer i = 1;

    assertFalse(p1.equals(i));
  }
}
