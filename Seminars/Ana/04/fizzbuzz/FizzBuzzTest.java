package fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    private final FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void testMultipleOf15() {
        assertEquals("FizzBuzz", fizzBuzz.evaluate(15));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(30));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(150));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(60));
    }

    @Test
    public void testContains3And5() {
        assertEquals("FizzBuzz", fizzBuzz.evaluate(35));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(53));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(153));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(555553));
    }

    @Test
    public void testMultipleOf3() {
        assertEquals("Fizz", fizzBuzz.evaluate(3));
        assertEquals("Fizz", fizzBuzz.evaluate(9));
        assertEquals("Fizz", fizzBuzz.evaluate(12));
        assertEquals("Fizz", fizzBuzz.evaluate(999));
    }

    @Test
    public void testContains3() {
        assertEquals("Fizz", fizzBuzz.evaluate(37));
        assertEquals("Fizz", fizzBuzz.evaluate(932));
        assertEquals("Fizz", fizzBuzz.evaluate(333331));
        assertEquals("Fizz", fizzBuzz.evaluate(1231));
    }

    @Test
    public void testMultipleOf5() {
        assertEquals("Buzz", fizzBuzz.evaluate(5));
        assertEquals("Buzz", fizzBuzz.evaluate(10));
        assertEquals("Buzz", fizzBuzz.evaluate(455));
        assertEquals("Buzz", fizzBuzz.evaluate(10000));
    }

    @Test
    public void testContains5() {
        assertEquals("Buzz", fizzBuzz.evaluate(5));
        assertEquals("Buzz", fizzBuzz.evaluate(511));
        assertEquals("Buzz", fizzBuzz.evaluate(2225));
        assertEquals("Buzz", fizzBuzz.evaluate(888577));
    }

    @Test
    public void testOther() {
        assertEquals("7", fizzBuzz.evaluate(7));
        assertEquals("74", fizzBuzz.evaluate(74));
        assertEquals("8888", fizzBuzz.evaluate(8888));
    }
}