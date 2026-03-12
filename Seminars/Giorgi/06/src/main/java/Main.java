public class Main {

    public static void main(String[] args) {
        RationalNumber r1 = new RationalNumber(2, 4);

        System.out.println(r1.evaluate());

        RationalNumber r2 = new RationalNumber(1, -2);
        System.out.println(r2.evaluate());

        System.out.println(r1.add(r2).evaluate()); // add
        System.out.println(r1.divide(r2)); // divide
    }
}
