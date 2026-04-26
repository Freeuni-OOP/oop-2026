package main.java;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GameOfLife {
    private static int NUM_ITERATIONS = 5;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int n = 5;
        int m = 5;
        boolean[][] grid = new boolean[n][m];
        initGrid(grid);
        Thread[][] cells = new Thread[n][m];
        CyclicBarrier barrier = new CyclicBarrier(n * m, () -> {
            printGrid(grid);
        });
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Thread t = new Thread(new Cell(i, j, grid, barrier));
                cells[i][j] = t;
                cells[i][j].start();
            }
        }
    }

    private static void initGrid(boolean[][] grid) {
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    private static void printGrid(boolean[][] grid) {
        System.out.println("------------------------------------"); // todopr: clear
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------"); // todopr: clear

    }

    private static class Cell implements Runnable {
        boolean[][] grid;
        private int x;
        private int y;
        private CyclicBarrier barrier;

        public Cell(int x, int y, boolean[][] grid, CyclicBarrier barrier) {
            this.x = x;
            this.y = y;
            this.grid = grid;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                boolean nextState = getNextState();
                try {
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                grid[x][y] = nextState;
                try {
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private boolean getNextState() {
            int numAlive = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i != 0 && j != 0) &&
                        x + i >= 0 && y + j >= 0 &&
                        x + i < grid.length &&
                        y + j < grid[0].length &&
                        grid[x + i][y + j]
                    )
                    {
                        numAlive++;
                    }
                }
            }
            if (numAlive < 2)
                return false;
            return numAlive == 2 || numAlive == 3;
        }
    }
}
