import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMyStack {

  private MyStack<String> myStack;

  @BeforeEach
  public void setUp() {
    myStack = new MyStack<>();
  }

  @Test
  public void testCreateMyStack() {
    MyStack myStack = new MyStack();
  }

  @Test
  public void testAdd() {
    myStack.add("a");
  }

  @Test
  public void testAddAndSizeCorrect() {
    assertEquals(0, myStack.size());
    myStack.add("a");
    assertEquals(1, myStack.size());
  }

  @Test
  public void testPop() {
    myStack.add("a");
    myStack.add("b");
    assertEquals("b", myStack.pop());
    assertEquals("a", myStack.pop());
  }

  @Test
  public void addManyElements() {
    for (int i = 1; i <= 100; i++) {
      myStack.add("a" + i);
      assertEquals(i, myStack.size());
    }
  }

  @Test
  public void popManyElements() {
    for (int i = 1; i <= 100; i++) {
      myStack.add("a" + i);
    }
    for (int i = 100; i >= 1; i--) {
      assertEquals("a" + i, myStack.pop());
      assertEquals(i - 1, myStack.size());
    }
  }

  @Test
  public void testPopEmptyStack() {
    Assertions.assertThrows(MyEmptyStackException.class, () -> myStack.pop());
  }

  @Test
  public void addAll() {
    List<String> list = List.of("a", "b", "c");
    myStack.addAll(list);
    assertEquals(3, myStack.size());
  }

  @Test
  public void addAllAndPop() {
    List<String> list = List.of("a", "b", "c");
    myStack.addAll(list);
    assertEquals("c", myStack.pop());
    assertEquals("b", myStack.pop());
    assertEquals("a", myStack.pop());
  }
}
