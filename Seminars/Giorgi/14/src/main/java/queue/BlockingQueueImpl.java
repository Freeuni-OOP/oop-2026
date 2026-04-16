package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueImpl<T> implements BlockingQueue<T> {

    private final List<T> elements;

    private final int maxSize;

    private final Lock lock;

    private final Condition enqueueCondition;

    private final Condition dequeueCondition;

    public BlockingQueueImpl(int maxSize) {
        elements = new ArrayList<>();
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.enqueueCondition = this.lock.newCondition();
        this.dequeueCondition = this.lock.newCondition();
    }

    @Override
    public void tryEnqueue(T elem, int time) {
        try {
            lock.lock();

            boolean okToEnqueue;

            while (elements.size() == maxSize) {
                okToEnqueue = enqueueCondition.await(time, TimeUnit.SECONDS);

                if (!okToEnqueue) {
                    System.out.println(Thread.currentThread().getName() + " couldn't enqueue element: " + elem);
                    return;
                }
            }

            elements.add(elem);
            System.out.println("enqueued: " + elem);

            dequeueCondition.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T tryDequeue(int time) {
        try {
            lock.lock();

            boolean okToDequeue;

            while (elements.isEmpty()) {
                okToDequeue = dequeueCondition.await(time, TimeUnit.SECONDS);

                if (!okToDequeue) {
                    System.out.println(Thread.currentThread().getName() + " couldn't dequeue ");
                    return null;
                }
            }

            T elem = elements.remove(0);
            System.out.println("dequeued: " + elem);

            enqueueCondition.signal();
            return elem;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}