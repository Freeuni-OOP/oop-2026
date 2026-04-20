import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {

    private static final int NUM_RACERS = 100;

    private static class Racer extends Thread {

        private int id;
        private CyclicBarrier start;
        private CountDownLatch finishLatch;
        private List<Integer> order;

        public Racer(int id,
                     CyclicBarrier start,
                     CountDownLatch finishLatch,
                     List<Integer> order
        ) {
            this.id = id;
            this.start = start;
            this.finishLatch = finishLatch;
            this.order = order;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

            // ---------- race start ------------------
            Random random = new Random();
            try {
                System.out.println("Running: " + id);
                Thread.sleep(2000 + random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // ---------- race end ------------------

            order.add(id);

            finishLatch.countDown(); // signals main
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier start = new CyclicBarrier(NUM_RACERS + 1, () -> {
            System.out.println("Race started!");
        });
        CountDownLatch finishLatch = new CountDownLatch(NUM_RACERS);

        List<Integer> order = new Vector<>();

        Racer[] racers = new Racer[NUM_RACERS];
        for (int i = 0; i < NUM_RACERS; i++) {
            racers[i] = new Racer(i, start, finishLatch, order);
            racers[i].start();
        }

        start.await();

        finishLatch.await();

        System.out.println("Race finished!\n");
        System.out.println("Top 3: ");

        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + ": " + order.get(i));
        }
    }
}
