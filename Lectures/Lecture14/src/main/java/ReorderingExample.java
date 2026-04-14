public class ReorderingExample {
    /*
        JVM uses JIT compiler.
        JIT compiler optimization example:

        for (int i = 0; i < 3; i++) {
            sum += arr[i];
        }
        -->
        sum += arr[0];
        sum += arr[1];
        sum += arr[2];

        Reduces loop overhead (no increment/check each time)
     */

    private static boolean ready = false;

    private static int answer = 0;

    static void computeAnswer() {
        // (JIT) possible reordering: 1. ready = true, 2. answer = 42
        answer = 42;
        ready = true;
    }

    static void useAnswer() {
        while (!ready) {
            Thread.yield();
        }
        if (answer == 0) { // exception may happen when reordering happens above
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
