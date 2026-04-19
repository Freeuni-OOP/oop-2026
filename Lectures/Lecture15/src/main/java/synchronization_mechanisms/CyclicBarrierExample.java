package synchronization_mechanisms;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3, () ->
                System.out.println("=== All students finished this stage ===\n")
        );

        Runnable student = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " writing...");
                Thread.sleep(1000);
                barrier.await();   // step 1 done.

                System.out.println(Thread.currentThread().getName() + " testing...");
                Thread.sleep(1000);
                barrier.await();   // step 2 done.

                System.out.println(Thread.currentThread().getName() + " presenting...");
                Thread.sleep(1000);
                barrier.await();   // step 3 done.

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(student, "Student 1").start();
        new Thread(student, "Student 2").start();
        new Thread(student, "Student 3").start();
    }
}