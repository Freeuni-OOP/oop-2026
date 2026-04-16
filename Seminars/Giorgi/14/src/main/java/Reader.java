import java.util.concurrent.Semaphore;

public class Reader extends Processor {

    public Reader(char[] buffer, Semaphore readableCells, Semaphore availableCells) {
        super(buffer, readableCells, availableCells);
    }

    @Override
    public void run() {

        int index = 0;

        while (!isInterrupted()) {
            try {
                readableCells.acquire();

                sleep(1000);
                System.out.println("read: " + buffer[index % buffer.length]);

                index++;

                availableCells.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
