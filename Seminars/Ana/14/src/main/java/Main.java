package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        List<Character> buffer = new ArrayList<>();
        int bufferSize = 10;
        Semaphore newCharacter = new Semaphore(0);
        Reader reader = new Reader(buffer, bufferSize, newCharacter);
        Writer writer = new Writer(buffer, bufferSize, newCharacter);

        Thread readerThread = new Thread(reader);
        Thread writerThread = new Thread(writer);

        readerThread.start();
        writerThread.start();

        try {
            readerThread.join();
            writerThread.join();
        } catch (InterruptedException e) {
        }
    }
}
