import java.util.Arrays;

public class BitmaskFilter implements Filter {

  // int[i] - 10000000 00000000 00000000 00000000 | 01000000 00000000 00000000 00000000 | 00000000 10000000 00000000 00000000
  private int[] bitmask;
  int count;

  public BitmaskFilter() {
    this.bitmask = new int[4];
    this.count = 0;
  }

  private void grow(int index) {
    int newSize = bitmask.length * 2 > index ? bitmask.length * 2 : 2 * index;
    bitmask = Arrays.copyOf(bitmask, newSize);
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public boolean isPresent(int index) {
    int arrayIndex = index / 32;
    int bitIndex = index % 32;

    int position = bitmask[arrayIndex];
    return (position & (1 << bitIndex)) != 0;
  }

  @Override
  public void add(int index) {
    int arrayIndex = index / 32;
    int bitIndex = index % 32;

    if (index < 0) {
      throw new FilterIndexNotFoundException("Negative index");
    }

    if (arrayIndex >= bitmask.length) {
      grow(arrayIndex);
    }

    int position = bitmask[arrayIndex];
    if ((position & (1 << bitIndex)) != 0) {
      return;
    }
    bitmask[arrayIndex] = position | (1 << bitIndex);
    count++;
  }

  @Override
  public void remove(int index) {
    int arrayIndex = index / 32;
    int bitIndex = index % 32;

    int position = bitmask[arrayIndex];
    if ((position & (1 << bitIndex)) == 0) {
      return;
    }
    bitmask[arrayIndex] = position & ~(1 << bitIndex);
    count--;
  }
}
