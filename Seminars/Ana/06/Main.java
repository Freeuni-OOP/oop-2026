public class Main {
    public static void main(String[] args) {
        //        RationalNumber r = new RationalNumber(5, 0);
        RationalNumber r1 = new RationalNumber(1, 2);
        System.out.println(r1);
        RationalNumber r3 = new RationalNumber(3, 6);
        System.out.println(r1.equals(r3));
        System.out.println(r3);

        RationalNumber r4 = new RationalNumber(3, 49);
        RationalNumber r5 = new RationalNumber(49, 3);

        RationalNumber r6 = new RationalNumber(0, 10);
        RationalNumber r7 = new RationalNumber(0, 20);

        System.out.println(r5.multiply(r6));

    }
}