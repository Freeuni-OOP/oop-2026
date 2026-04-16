import java.util.Random;
import java.util.concurrent.Semaphore;

public class Writer extends Processor {

    public Writer(char[] buffer, Semaphore readableCells, Semaphore availableCells) {
        super(buffer, readableCells, availableCells);
    }

    @Override
    public void run() {
        int index = 0;

        while (!isInterrupted()) {
            try {
                availableCells.acquire();

                Random random = new Random();
                char ch = (char) ('a' + (Math.abs(random.nextInt()) % 26));
                buffer[index % buffer.length] = ch;
                System.out.println("wrote: " + ch);

                index++;

                readableCells.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
