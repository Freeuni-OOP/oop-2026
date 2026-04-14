package main.java;

public interface BlockingQueue<T> {
    public void enqueue(T t) throws InterruptedException;

    public T dequeue() throws InterruptedException;

    public boolean tryEnqueue(T t, int millis) throws InterruptedException;

    public T tryDequeue(int millis) throws InterruptedException;

    public int size();
}
