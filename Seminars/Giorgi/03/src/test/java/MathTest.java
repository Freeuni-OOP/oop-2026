import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {

    @Test
    public void testAddSmall() {
        assertEquals(5, Math.add(3, 2));
        assertEquals(4, Math.add(2, 2));
        assertEquals(1, Math.add(0, 1));
    }

    @Test
    public void testAddLimits() {
        assertEquals(-1, Math.add(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    public void testAddNegatives() {
        assertEquals(-3, Math.add(-2, -1));
        assertEquals(-2, Math.add(-1, -1));
    }

    @Test
    public void testAddNegativesPositives() {
        assertEquals(0, Math.add(-5, 5));
        assertEquals(1, Math.add(-2, 3));
    }

    @Test
    public void testAbsSmall() {
        assertEquals(1, Math.abs(-1));
        assertEquals(2, Math.abs(2));
        assertEquals(3, Math.abs(-3));
    }

    @Test
    public void testAbsBig() {
        assertEquals(Integer.MAX_VALUE, Math.abs(-Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, Math.abs(Integer.MIN_VALUE + 1));
    }

    @Test
    public void testAbsZero() {
        assertEquals(0, Math.abs(0));
    }
}
