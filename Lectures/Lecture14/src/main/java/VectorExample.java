import java.util.List;
import java.util.Vector;

public class VectorExample {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> vector = new Vector<>(); // ArrayList

        // Each thread adds 1000 elements in the vector
        int numThreads = 10;
        int perThreadNumElements = 1000;

        Thread[] threads = new Thread[numThreads];

        // Create threads that add elements
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < perThreadNumElements; j++) {
                    vector.add(j);
                }
            });
        }

        // Start threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads
        for (Thread t : threads) {
            t.join();
        }

        // Expected size = numThreads * perThreadNumElements
        System.out.println("Expected size: " + (numThreads * perThreadNumElements));
        System.out.println("Actual size:   " + vector.size());
    }
}