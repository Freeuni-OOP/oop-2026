import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueue<T> implements BlockingQueue<T> {

    private final int capacity;
    private final List<T> elements;
    private final Lock lock;
    private final Condition putCondition;
    private final Condition takeCondition;

    public ProducerConsumerQueue(int capacity) {
        this.capacity = capacity;
        elements = new ArrayList<>();
        lock = new ReentrantLock();
        putCondition = lock.newCondition();
        takeCondition = lock.newCondition();
    }

    @Override
    public void put(T elem) {
        lock.lock();

        while (elements.size() == capacity) {
            try {
                takeCondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        elements.add(elem);
        putCondition.signalAll();

        lock.unlock();
    }

    @Override
    public T take() {
        try {

            lock.lock();

            while (elements.isEmpty()) {
                putCondition.await();
            }

            T lastElement = elements.getFirst();
            elements.removeFirst();
            takeCondition.signalAll();

            return lastElement;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
