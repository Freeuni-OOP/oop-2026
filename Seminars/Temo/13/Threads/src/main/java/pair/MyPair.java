package pair;

public class MyPair<T1, T2> {

  private T1 first;
  private T2 second;

  public MyPair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }

  public synchronized void setPair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }

  public synchronized T1 getFirst() {
    return first;
  }

  public synchronized T2 getSecond() {
    return second;
  }

}
