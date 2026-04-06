public class Pair {

    private int first;

    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public synchronized void increment() {
        this.first++;
        this.second++;
        System.out.println("sum: " + getSum());
    }

    public synchronized int getSum() {
        return this.first + this.second;
    }

}