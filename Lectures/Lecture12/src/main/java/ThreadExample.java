public class ThreadExample {

    private static class Worker extends Thread {

        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("running thread: " + getName());

            long sum = 0;

            for (int i = 0; i < 1000; i++) {
                sum = sum + i;

                if (i % 10 == 0) {
                    Thread running = Thread.currentThread();
                    System.out.println(running.getName() + " iteration: " + i);
                }
            }

            System.out.println("total sum: " + sum);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Worker("my thread");
        thread.start();
    }
}
