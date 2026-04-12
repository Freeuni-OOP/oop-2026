package main.java;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker("" + i);
            worker.start();
        }
    }

    private static class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 1000; i++) {
                System.out.println("running thread: " + Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
