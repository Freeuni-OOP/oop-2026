package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    private int permits;
    private Lock lock;
    private Condition newPermit;

    public MySemaphore(int permits) {
        this.permits = permits;
        lock = new ReentrantLock();
        newPermit = lock.newCondition();
    }

    public static void main(String[] args) throws InterruptedException {
        MySemaphore semaphore = new MySemaphore(0);

        Thread w1 = new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        w1.start();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread w = new Thread(semaphore::release);
            w.start();
            threads.add(w);
        }
        w1.join();
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(semaphore.permits); // 9999
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        while (permits == 0) {
            newPermit.await();
        }
        permits--;
        lock.unlock();
    }

    public void release() {
        lock.lock();
        permits++;
        newPermit.signal();
        lock.unlock();
    }
}
