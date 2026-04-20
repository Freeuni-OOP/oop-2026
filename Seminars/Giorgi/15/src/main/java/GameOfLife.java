import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GameOfLife {

    private static final int NUM_ROUNDS = 10;

    private static boolean[][] grid;
    private static CyclicBarrier barrier;

    private static class Cell extends Thread {
        private final int x;
        private final int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private boolean getNextState() {
            int numAlives = 0;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int newX = x + i;
                    int newY = y + j;

                    if (newX >= 0 && newX < grid.length
                            && newY >= 0 && newY < grid[0].length
                            && grid[newX][newY]) {
                        numAlives++;
                    }
                }
            }

            return numAlives == 3 || (grid[x][y] && numAlives == 2);
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_ROUNDS; i++) {
                boolean nextState = getNextState();

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

                grid[x][y] = nextState;

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {
        int n = 4, m = 5;

        grid = new boolean[n][m];
        initGrid(n, m);

        barrier = new CyclicBarrier(n * m, () -> {
            printGrid(n, m);
        });

        Cell[][] cells = new Cell[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].start();
            }
        }
    }

    private static void printGrid(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    private static void initGrid(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Random random = new Random();
                grid[i][j] = random.nextBoolean();
            }
        }
    }
}
