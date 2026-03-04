import java.util.Arrays;
import java.util.Collection;

public class MyStack<T> {

  private T[] stack;
  private int loglen;

  public MyStack() {
    stack = (T[]) new Object[4];
    loglen = 0;
  }

  public void add(T element) {
    stack[loglen] = element;
    if (loglen == stack.length - 1) {
      stack = Arrays.copyOf(stack, stack.length * 2);
    }
    loglen++;
  }

  public T pop() {
    if (this.size() == 0) {
      throw new MyEmptyStackException();
    }
    return stack[--loglen];
  }

  public void addAll(Collection<T> elements) {
    for (T element : elements) {
      this.add(element);
    }
  }

  public int size() {
    return loglen;
  }
}
