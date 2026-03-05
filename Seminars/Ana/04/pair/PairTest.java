package pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairTest {
    @Test
    public void testEquals() {
        Pair<Integer, String> pair1 = new Pair<>(1, "a");
        Pair<Integer, String> pair2 = new Pair<>(1, "a");
        assertTrue(pair1.equals(pair2));
        assertTrue(pair2.equals(pair1));
    }

    @Test
    public void testNotEquals() {
        Pair<Integer, String> pair1 = new Pair<>(1, "a");
        Pair<Integer, String> pair2 = new Pair<>(1, "b");
        assertFalse(pair1.equals(pair2));
        assertFalse(pair2.equals(pair1));
    }

    @Test
    public void testSetAndEquals() {
        Pair<Character, String> pair1 = new Pair<>();
        pair1.setKey('c');
        pair1.setValue("c");
        Pair<Character, String> pair2 = new Pair<>('c', "c");
        assertTrue(pair1.equals(pair2));
        assertTrue(pair2.equals(pair1));
    }

    @Test
    public void testSetAndNotEquals() {
        Pair<Character, String> pair1 = new Pair<>();
        pair1.setKey('a');
        pair1.setValue("a");
        Pair<Character, String> pair2 = new Pair<>('a', "b");
        assertFalse(pair1.equals(pair2));
        assertFalse(pair2.equals(pair1));
    }

    @Test
    public void testSetAndGet() {
        Pair<String, Integer> pair1 = new Pair<>();
        pair1.setKey("k");
        pair1.setValue(50);
        assertEquals("k", pair1.getKey());
        assertEquals(50, pair1.getValue());
    }

    @Test
    public void testHashCodeEquals() {
        Pair<String, Integer> pair1 = new Pair<>("A", 10);
        Pair<String, Integer> pair2 = new Pair<>();
        pair2.setKey("A");
        pair2.setValue(10);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    public void testToString() {
        Pair<String, Integer> pair1 = new Pair<>("A", 10);
        assertEquals("A=10", pair1.toString());
    }
}