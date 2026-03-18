import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFilter implements Filter {

  private List<Integer> list;

  public ListFilter() {
    this.list = new ArrayList<>();
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isPresent(int index) {
    return Collections.binarySearch(list, index) >= 0;
  }

  @Override
  public void add(int index) {
    if (index < 0) {
      throw new FilterIndexNotFoundException("Negative index");
    }
    int insertionIndex = -(Collections.binarySearch(list, index) + 1);
    if (insertionIndex >= 0) {
      list.add(insertionIndex, index);
    }
  }

  @Override
  public void remove(int index) {
    int removeIndex = Collections.binarySearch(list, index);
    if (removeIndex < 0) {
      return;
    }
    list.remove(removeIndex);
  }
}
