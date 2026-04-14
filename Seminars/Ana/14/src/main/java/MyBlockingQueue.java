package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> implements BlockingQueue<T> {
    private List<T> queue;
    private int maxSize;
    private Lock lock;
    private Condition enqueueCondition;
    private Condition dequeueCondition;

    public MyBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new ArrayList<T>();
        lock = new ReentrantLock();
        enqueueCondition = lock.newCondition();
        dequeueCondition = lock.newCondition();
    }

    @Override
    public void enqueue(T t) throws InterruptedException {
        lock.lock();
        while (queue.size() >= maxSize) {
            enqueueCondition.await();
        }
        queue.add(t);
        dequeueCondition.signal();
        lock.unlock();
    }

    @Override
    public T dequeue() throws InterruptedException {
        lock.lock();
        while (queue.isEmpty()) {
            dequeueCondition.await();
        }
        T t = queue.removeFirst();
        enqueueCondition.signal();
        lock.unlock();
        return t;
    }

    @Override
    public boolean tryEnqueue(T t, int millis) throws InterruptedException {
        lock.lock();
        boolean result;
        while (queue.size() >= maxSize) {
            result = enqueueCondition.await(millis, TimeUnit.MILLISECONDS);
            if (!result) {
                System.out.println(Thread.currentThread().getName() + " failed to enqueue " + t);
                return false;
            }
        }
        queue.add(t);
        dequeueCondition.signal();
        lock.unlock();
        return true;
    }

    @Override
    public T tryDequeue(int millis) throws InterruptedException {
        lock.lock();
        boolean result;
        while (queue.isEmpty()) {
            result = dequeueCondition.await(millis, TimeUnit.MILLISECONDS);
            if (!result) {
                System.out.println(Thread.currentThread().getName() + " failed to dequeue");
                return null;
            }
        }
        T t = queue.removeFirst();
        enqueueCondition.signal();
        lock.unlock();
        return t;
    }

    @Override
    public int size() {
        lock.lock();
        int size = queue.size();
        lock.unlock();
        return size;
    }
}
