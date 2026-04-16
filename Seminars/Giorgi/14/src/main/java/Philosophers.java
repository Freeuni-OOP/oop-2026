import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophers {

    private static final int NUM_PHILOSOPHERS = 5;

    private static class Philosopher extends Thread {

        private final int id;
        private final Semaphore numPlaces;
        private final Lock[] forks;

        public Philosopher(int id, Semaphore numPlaces, Lock[] forks) {
            this.id = id;
            this.numPlaces = numPlaces;
            this.forks = forks;
        }

        public void think() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " thinking");
            Thread.sleep(1000);
        }

        public void eat() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " eating");
            Thread.sleep(1000);
        }

        public void run() {
            // id = 4 --> forks: 4 and 0
            while (!isInterrupted()) {
                try {
                    think();
                    numPlaces.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                forks[id].lock();
                forks[(id + 1) % NUM_PHILOSOPHERS].lock();

                try {
                    eat();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                forks[id].unlock();
                forks[(id + 1) % NUM_PHILOSOPHERS].unlock();
                numPlaces.release();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Semaphore numPlaces = new Semaphore(NUM_PHILOSOPHERS - 1);
        Lock[] forks = new ReentrantLock[NUM_PHILOSOPHERS]; // num forks = NUM_PHILOSOPHERS

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, numPlaces, forks);
            forks[i] = new ReentrantLock();
        }

        // start process
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i].start();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i].join();
        }

        System.out.println("All philosophers done.");
    }
}
