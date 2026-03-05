import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {

    @ParameterizedTest
    @CsvSource({
            "0, FizzBuzz",
            "90, FizzBuzz",
            "45, FizzBuzz",
            "60, FizzBuzz",
    })
    public void testDiv15(int number, String expected) {
        assertEquals(expected, FizzBuzz.evaluate(number));
    }

    @Test
    public void testContains3Or5() {
        assertEquals("FizzBuzz", FizzBuzz.evaluate(35));
        assertEquals("FizzBuzz", FizzBuzz.evaluate(53));
        assertEquals("FizzBuzz", FizzBuzz.evaluate(135));
    }

    @Test
    public void testDiv3() {
        assertEquals("Fizz", FizzBuzz.evaluate(3));
        assertEquals("Fizz", FizzBuzz.evaluate(9));
        assertEquals("Fizz", FizzBuzz.evaluate(51));
    }

    @Test
    public void testContains3() {
        assertEquals("Fizz", FizzBuzz.evaluate(31));
        assertEquals("Fizz", FizzBuzz.evaluate(13));
        assertEquals("Fizz", FizzBuzz.evaluate(130));
    }

    @Test
    public void testDiv5() {
        assertEquals("Buzz", FizzBuzz.evaluate(5));
        assertEquals("Buzz", FizzBuzz.evaluate(55));
        assertEquals("Buzz", FizzBuzz.evaluate(20));
    }

    @Test
    public void testContains5() {
        assertEquals("Buzz", FizzBuzz.evaluate(52));
        assertEquals("Buzz", FizzBuzz.evaluate(1555555));
    }

    @Test
    public void testOthers() {
        assertEquals("17", FizzBuzz.evaluate(17));
        assertEquals("29", FizzBuzz.evaluate(29));
    }
}
