import java.util.Arrays;

public class BooleanFilter implements Filter {

  private boolean[] filter;
  int count;

  public BooleanFilter() {
    this.filter = new boolean[4];
    this.count = 0;
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public boolean isPresent(int index) {
    if (index >= filter.length || index < 0) {
      return false;
    }
    return filter[index];
  }

  private void grow(int index) {
    int newSize = filter.length * 2 > index ? filter.length * 2 : 2 * index;
    filter = Arrays.copyOf(filter, newSize);
  }

  @Override
  public void add(int index) {
    if (index >= filter.length) {
      grow(index);
    }
    if (index < 0) {
      throw new FilterIndexNotFoundException("Negative index");
    }
    if (filter[index]) {
      return;
    }
    filter[index] = true;
    count++;
  }

  @Override
  public void remove(int index) {
    if (index >= filter.length || index < 0 || !filter[index]) {
      return;
    }
    filter[index] = false;
    count--;
  }
}
