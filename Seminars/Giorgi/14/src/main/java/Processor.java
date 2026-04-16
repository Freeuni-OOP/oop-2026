import java.util.concurrent.Semaphore;

public abstract class Processor extends Thread {
    private static final int NUM_CHARACTERS = 10;

    protected char[] buffer;

    protected Semaphore readableCells; // for reader

    protected Semaphore availableCells; // for writer

    public Processor(char[] buffer,
                     Semaphore readableCells,
                     Semaphore availableCells) {
        this.buffer = buffer;
        this.readableCells = readableCells;
        this.availableCells = availableCells;
    }

    public static void main(String[] args) throws InterruptedException {
        char[] buffer = new char[NUM_CHARACTERS];
        Semaphore readableCells = new Semaphore(0);
        Semaphore availableCells = new Semaphore(buffer.length);

        Processor writer = new Writer(buffer, readableCells, availableCells);
        Processor reader = new Reader(buffer, readableCells, availableCells);

        System.out.println("Started the process: ");

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        System.out.println("Finish. ");
    }
}
