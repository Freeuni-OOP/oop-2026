import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PairTest {

    private Pair<Integer, String> pair1;

    @BeforeEach
    public void setUp() {
        pair1 = new Pair<>(1, "1");
    }

    @Test
    public void testGetters() {
        assertEquals(1, pair1.getFirst());
        assertEquals("1", pair1.getSecond());

        pair1.setFirst(2);
        pair1.setSecond("2");
        assertEquals(2, pair1.getFirst());
    }

    @Test
    public void testEquals() {
        Pair<Number, String> pair2 = new Pair<>(1.0, "1");
        assertNotEquals(pair1, pair2);
    }

    @Test
    public void testEqualsNull() {
        Pair<Number, String> pair2 = new Pair<>(null, "1");
        assertNotEquals(pair2, pair1);
    }
}
