public class Pair {

    private int first;

    private int second;

    public static int staticVariable;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public void increment() {
        this.first++;
        this.second++;
    }

    public int getSum() {
        return this.first + this.second;
    }

    public void increaseStaticVariable() { // non-static
        synchronized (Pair.class) {
            staticVariable++;
        }
    }

    public synchronized static void staticIncrement() { // static
        staticVariable++;
    }
}