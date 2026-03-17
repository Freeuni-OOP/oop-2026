import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {
    private Filter filter;

    @BeforeEach
    public void setUp() {
        filter = new BitMaskFilter();
    }

    @Test
    public void testNewlyCreatedFilterHasSizeZero() {
        assertEquals(0, filter.size());
    }

    @Test
    public void testAddedElementIsPresent() {
        filter.add(20);
        assertTrue(filter.isPresent(20));

        filter.add(50);
        assertTrue(filter.isPresent(50));
    }

    @Test
    public void testNotAddedElementNotPresent() {
        assertFalse(filter.isPresent(1));
        assertFalse(filter.isPresent(2));
    }

    @Test
    public void testSizeAfterAdd() {
        filter.add(1);
        assertEquals(1, filter.size());
        filter.add(2);
        assertEquals(2, filter.size());
        filter.add(7);
        assertEquals(3, filter.size());
    }

    @Test
    public void testAddingDuplicatesDontIncreaseSize() {
        filter.add(7);
        assertEquals(1, filter.size());

        filter.add(7);
        filter.add(7);
        assertEquals(1, filter.size());
    }

    @Test
    public void testRemovedElementNotPresent() {
        filter.add(7);
        filter.remove(7);
        assertFalse(filter.isPresent(7));
    }

    @Test
    public void testRemovingElementDecreasesSize() {
        filter.add(1);
        filter.add(2);
        filter.add(3);
        assertEquals(3, filter.size());

        filter.remove(1);
        assertEquals(2, filter.size());

        filter.remove(2);
        filter.remove(3);
        assertEquals(0, filter.size());
    }

    @Test
    public void testRemoveOnEmptyFilter() {
        filter.remove(5);
        assertFalse(filter.isPresent(5));
        assertEquals(0, filter.size());
    }

    @Test
    public void testRemoveMultipleTimes() {
        filter.add(3);
        filter.add(5);
        filter.remove(5);
        filter.remove(5);

        assertEquals(1, filter.size());
    }
}