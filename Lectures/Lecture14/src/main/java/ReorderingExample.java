public class ReorderingExample {

    private static boolean ready = false;

    private static int answer = 0;

    static void computeAnswer() {
        // possible reordering: 1. ready = true, 2. answer = 42
        answer = 42;
        ready = true;
    }

    static void useAnswer() {
        while (!ready) {
            Thread.yield();
        }
        if (answer == 0) { // exception may happen when reordering above
            throw new RuntimeException("!!!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ReorderingExample::computeAnswer);
        Thread t2 = new Thread(ReorderingExample::useAnswer);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
