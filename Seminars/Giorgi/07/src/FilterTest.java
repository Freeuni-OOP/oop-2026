import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    private Filter filter;

    @BeforeEach
    public void setUp() {
        filter = new BooleanFilter();
    }

    @Test
    public void testAdd() {
        filter.add(31);
        assertTrue(filter.isPresent(31));
        assertEquals(1, filter.size());
    }

    @Test
    public void testAddMany() {
        for (int i = 0; i < 31; i++) {
            filter.add((int) Math.pow(2, i));
        }

        assertEquals(31, filter.size());
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 31; i++) {
            filter.add((int) Math.pow(2, i));
        }
        for (int i = 30; i >= 0; i--) {
            filter.remove((int) Math.pow(2, i));
            filter.remove((int) Math.pow(2, i));
            assertFalse(filter.isPresent((int) Math.pow(2, i)));
            assertEquals(i, filter.size());
        }
    }
}
