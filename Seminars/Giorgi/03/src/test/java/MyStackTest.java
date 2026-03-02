import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyStackTest {

    private MyStack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new MyStack<>();
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.add("1");
        stack.add("2");
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    public void testSizeMany() {
        for (int i = 1; i <= 100000; i++) {
            stack.add("" + i);
            assertEquals(i, stack.size());
        }

        for (int i = 100000; i >= 1; i--) {
            stack.pop();
            assertEquals(i - 1, stack.size());
        }
    }

    @Test
    public void testAdd() {
        stack.add("1");
        stack.add("2");
        stack.add("3");
        assertEquals(3, stack.size());
        assertEquals("3", stack.pop());
        assertEquals(2, stack.size());
    }

    @Test
    public void testPop() {
        stack.add("1");
        stack.add("2");
        stack.add("3");
        assertEquals("3", stack.pop());
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
    }

    @Test
    public void testPopEmpty() {
        assertThrows(RuntimeException.class, () -> stack.pop());
    }

    @Test
    public void testAddAll() {
        stack.addAll(Arrays.asList("1", "2", "3"));
        assertEquals(3, stack.size());
        assertEquals("3", stack.pop());
        assertEquals("2", stack.pop());
        stack.add("4");
        assertEquals("4", stack.pop());
        assertEquals(1, stack.size());
    }
}
