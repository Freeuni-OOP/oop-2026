package main.java;

public class RunnableExample implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            Thread t1 = new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    System.out.println(Thread.currentThread().getName() + " " + j);
                }
            });
            t1.start();
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
