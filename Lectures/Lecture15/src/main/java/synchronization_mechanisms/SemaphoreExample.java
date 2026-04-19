package synchronization_mechanisms;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final int NUM_AVAILABLE_PARKING_SLOTS = 3;

    public static void main(String[] args) {

        Semaphore parking = new Semaphore(NUM_AVAILABLE_PARKING_SLOTS);

        Runnable car = () -> {
            try {
                parking.acquire();
                System.out.println(Thread.currentThread().getName() + " parked");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " leaving");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                parking.release();
            }
        };

        for (int i = 1; i <= 10; i++) {
            new Thread(car, "Car " + i).start();
        }
    }
}