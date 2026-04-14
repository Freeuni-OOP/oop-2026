package main.java;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophers {
    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(NUM_PHILOSOPHERS - 1);
        Lock[] forks = new Lock[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, NUM_PHILOSOPHERS, semaphore, forks);
            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }
    }

    private static class Philosopher implements Runnable {
        private Semaphore semaphore;
        private Lock[] forks;
        private int id;
        private int numPhilosophers;

        public Philosopher(int id, int numPhilosophers, Semaphore semaphore, Lock[] forks) {
            this.id = id;
            this.numPhilosophers = numPhilosophers;
            this.semaphore = semaphore;
            this.forks = forks;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    think();
                    semaphore.acquire();
                    forks[id].lock();
                    forks[(id + 1) % numPhilosophers].lock();

                    eat();

                    forks[id].unlock();
                    forks[(id + 1) % numPhilosophers].unlock();

                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void think() throws InterruptedException {
            System.out.println(id + " is thinking");
            Thread.sleep((long) (Math.random() * 1000));
        }

        public void eat() throws InterruptedException {
            System.out.println(id + " is eating");
            Thread.sleep((long) (Math.random() * 1000));
        }
    }
}
