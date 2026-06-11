public interface BlockingQueue<T> {

    void put(T elem);

    T take();
}