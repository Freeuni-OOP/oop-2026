package synchronization_mechanisms;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch studentsArrived = new CountDownLatch(3);

        Runnable student = () -> {
            System.out.println(Thread.currentThread().getName() + " arrived");
            studentsArrived.countDown();
        };

        new Thread(student, "Student 1").start();
        new Thread(student, "Student 2").start();
        new Thread(student, "Student 3").start();

        System.out.println("Teacher waiting...");
        studentsArrived.await(); // wait for all students

        System.out.println("All students arrived. Exam starts!");
    }
}