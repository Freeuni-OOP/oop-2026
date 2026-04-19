package synchronization_mechanisms;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class BrokenBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3);

        Runnable student = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " arrived");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " continues");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + " sees broken barrier!");
            }
        };

        Thread t1 = new Thread(student, "Student 1");
        Thread t2 = new Thread(student, "Student 2");
        Thread t3 = new Thread(student, "Student 3");

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
            t1.interrupt();  // first student stops and gets interrupted exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // third student comes late
        t3.start();
    }
}