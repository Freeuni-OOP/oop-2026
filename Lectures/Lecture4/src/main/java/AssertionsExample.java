public class AssertionsExample {

    public static void main(String[] args) {
        System.out.println(sqrt(-1));
    }

    // tip: add -ea in vm options to enable assertions
    public static double sqrt(double x) {
        assert x >= 0;
        double r = Math.sqrt(x);
        assert Math.abs(r * r - x) < .0001;
        return r;
    }
}
