package main.java;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private List<Character> buffer;
    private int bufferSize;
    private Semaphore newCharacter;

    public Reader(List<Character> buffer, int bufferSize, Semaphore newCharacter) {
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.newCharacter = newCharacter;
    }

    @Override
    public void run() {
        for (int i = 0; i < bufferSize; i++) {
            try {
                newCharacter.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            char ch = buffer.get(i);
            System.out.println("Reader read: " + ch); // todopr: clear
        }
    }
}
