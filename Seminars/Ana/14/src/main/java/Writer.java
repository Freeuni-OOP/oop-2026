package main.java;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Writer implements Runnable {
    private List<Character> buffer;
    private int bufferSize;
    private Semaphore newCharacter;

    public Writer(List<Character> buffer, int bufferSize, Semaphore newCharacter) {
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.newCharacter = newCharacter;
    }

    @Override
    public void run() {
        for (int i = 0; i < bufferSize; i++) {
            char ch = (char) ('a' + i);
            buffer.add(ch);
            System.out.println("Writer wrote: " + ch);
            newCharacter.release();
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
