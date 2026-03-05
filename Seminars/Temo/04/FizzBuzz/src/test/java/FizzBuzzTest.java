import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

  @Test
  public void testFizzBuzz() {
    // 15, 30
    // 35, 53
    // 315
    List<Integer> numbers = List.of(15, 30, 35, 53, 315);
    for (Integer number : numbers) {
      assertEquals("FizzBuzz", FizzBuzz.evaluate(number));
    }
  }

  @Test
  public void testFizz() {
    // 3 6 13
    List<Integer> numbers = List.of(3, 6, 13);

    for (Integer number : numbers) {
      assertEquals("Fizz", FizzBuzz.evaluate(number));
    }
  }

  @Test
  public void testBuzz() {
    // 5 25 53
    List<Integer> numbers = List.of(5, 25, 52);
    for (Integer number : numbers) {
      assertEquals("Buzz", FizzBuzz.evaluate(number));
    }
  }

  @Test
  public void testNumberAsString() {
    // 4 11 17
    List<Integer> numbers = List.of(4, 11, 17);
    for (Integer number : numbers) {
      assertEquals(String.valueOf(number), FizzBuzz.evaluate(number));
    }
  }
}
