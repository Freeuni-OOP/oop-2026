package math;

import junit.*;

import static junit.Assertions.assertEquals;

public class MathTest {

    private Math math;

    @BeforeEach
    public void setUp() {
        math = new Math();
    }

    @Test
    public void testAdd() {
        assertEquals(2, math.getSum(1, 1));
    }

    @Test
    public void testMultiply() {
        assertEquals(1, math.getMultiply(1, 1));
    }

}
