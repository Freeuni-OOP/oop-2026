package queue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueueImpl<>(5);

        Thread[] producers = new Thread[10];
        for (int i = 0; i < 10; i++) {
            producers[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    blockingQueue.tryEnqueue(j, 1);
                }
            });
        }

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int element = blockingQueue.tryDequeue(1);
            }
        });

        for (int i = 0; i < 10; i++) {
            producers[i].start();
        }
        consumer.start();
    }
}