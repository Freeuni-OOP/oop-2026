package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Race {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new Racer(i, startLatch, finishLatch, list));
            t.start();
        }
        System.out.println("Race starts");
        startLatch.countDown();
        finishLatch.await();
        System.out.println("Race finished. View results:"); // todopr: clear
        for (int i = 0; i < 3; i++) {
            System.out.println(list.get(i));
        }
    }

    private static class Racer implements Runnable {
        private int id;
        private CountDownLatch startLatch;
        private CountDownLatch finishLatch;
        private List<Integer> order;

        public Racer(int id, CountDownLatch startLatch, CountDownLatch finishLatch, List<Integer> order) {
            this.id = id;
            this.startLatch = startLatch;
            this.finishLatch = finishLatch;
            this.order = order;
        }

        @Override
        public void run() {
            System.out.println("Racer " + id + " is getting ready");
            // wait for main
            try {
                startLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Racer " + id + " is driving");
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (order) {
                order.add(id);
            }
            System.out.println("Racer " + id + " finished race");
            finishLatch.countDown();
        }
    }
}
