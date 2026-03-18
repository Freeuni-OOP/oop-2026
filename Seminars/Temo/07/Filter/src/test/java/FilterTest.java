import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterTest {

  private Filter filter;

  @BeforeEach
  public void setUp() {
    filter = new BitmaskFilter();
  }

  @Test
  public void testSize() {
    Assertions.assertEquals(0, filter.size());
  }

  @Test
  public void testAddAndSize() {
    filter.add(1);
    Assertions.assertEquals(1, filter.size());
  }

  @Test
  public void testAddAndIsPresent() {
    filter.add(1);
    Assertions.assertTrue(filter.isPresent(1));
  }

  @Test
  public void testRemove() {
    filter.add(1);
    filter.remove(1);
    Assertions.assertEquals(0, filter.size());
  }

  @Test
  public void testRemoveNonExistentIndex() {
    Assertions.assertEquals(0, filter.size());
    filter.remove(1);
    Assertions.assertEquals(0, filter.size());
  }

  @Test
  public void testAddSameIndexMultipleTimes() {
    filter.add(1);
    filter.add(1);
    Assertions.assertEquals(1, filter.size());
  }

  @Test
  public void testAddManyElements() {
    for (int i = 0; i < 100; i++) {
      filter.add(i);
    }
    Assertions.assertEquals(100, filter.size());
    for (int i = 0; i < 100; i++) {
      Assertions.assertTrue(filter.isPresent(i));
    }
  }

  @Test
  public void testAddBigIndex() {
    filter.add(1);
    filter.add(1000);
    Assertions.assertEquals(2, filter.size());
    Assertions.assertTrue(filter.isPresent(1));
    Assertions.assertTrue(filter.isPresent(1000));
  }

  @Test
  public void testAddIllegalIndex() {
    Assertions.assertThrows(FilterIndexNotFoundException.class, () -> filter.add(-1));
  }
}
