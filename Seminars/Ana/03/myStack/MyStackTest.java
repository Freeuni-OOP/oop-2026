package myStack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyStackTest {
    MyStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new MyStack<>();
    }

    @Test
    public void testSizeOfEmptyStack() {
        assertEquals(0, stack.size());
    }

    @Test
    public void testAddOneElement() {
        assertEquals(0, stack.size());
        stack.add(20);
        assertEquals(1, stack.size());
    }

    @Test
    public void testAddManyElements() {
        for (int i = 0; i < 10_000; i++) {
            stack.add(i);
        }
        assertEquals(10_000, stack.size());
    }

    @Test
    public void testPopEmptyStack() {
        assertEquals(0, stack.size());
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    public void testPopReturnsLastElement() {
        stack.add(12);
        stack.add(17);
        assertEquals(17, stack.pop().intValue());
        assertEquals(1, stack.size());
    }

    @Test
    public void testAddAllEmptyCollection() {
        stack.addAll(List.of());
        assertEquals(0, stack.size());
    }

    @Test
    public void testAddAllEmptyCollection2() {
        stack.addAll(List.of());
        assertEquals(0, stack.size());
    }

    @Test
    public void testAddAll() {
        stack.addAll(List.of(1, 2, 3));
        assertEquals(3, stack.size());
        assertEquals(3, stack.pop().intValue());
    }
}