public class PairWorker extends Thread {

    private static final int NUM_ITERATIONS = 1000;

    private final Pair pair;

    public PairWorker(Pair pair) {
        this.pair = pair;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            pair.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Pair p = new Pair(0, 0);

        PairWorker w1 = new PairWorker(p);
        PairWorker w2 = new PairWorker(p);
        PairWorker w3 = new PairWorker(p);

        w1.start();
        w2.start();
        w3.start();

        Thread.sleep(10);

        w1.join();
        w2.join();
        w3.join();

        System.out.println(p.getSum()); // 6000000 expected
    }
}