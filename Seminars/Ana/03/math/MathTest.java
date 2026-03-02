package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathTest {
    @Test
    public void testAddSmallPositiveNumbers() {
        assertEquals(2, Math.add(1, 1));
        assertEquals(7, Math.add(3, 4));
        assertEquals(20, Math.add(15, 5));
    }

    @Test
    public void testAddSmallNegativeNumbers() {
        assertEquals(-2, Math.add(-1, -1));
        assertEquals(-7, Math.add(-3, -4));
    }

    @Test
    public void testAddSmallPositiveAndNegativeNumbers() {
        assertEquals(0, Math.add(1, -1));
        assertEquals(-1, Math.add(3, -4));
    }

    @Test
    public void testAddBigPositiveNumbers() {
        assertEquals(100_000, Math.add(50_000, 50_000));
    }

    @Test
    public void testAddMaxValues() {
        assertEquals(1, Math.add(-Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    public void testAbsSmallPositiveNumber() {
        assertEquals(2, Math.abs(2));
        assertEquals(100, Math.abs(100));
    }

    @Test
    public void testAbsSmallNegativeNumber() {
        assertEquals(2, Math.abs(-2));
        assertEquals(100, Math.abs(-100));
    }

    @Test
    public void testAbsBigPositiveNumber() {
        assertEquals(100_000_000, Math.abs(100_000_000));
        assertEquals(5555555, Math.abs(5555555));
    }

    @Test
    public void testAbsBigNegativeNumber() {
        assertEquals(5555555, Math.abs(-5555555));
        assertEquals(100_000_000, Math.abs(-100_000_000));
    }

    @Test
    public void testAbsMinValue() {
        assertThrows(IllegalArgumentException.class, () -> Math.abs(Integer.MIN_VALUE));
    }
}