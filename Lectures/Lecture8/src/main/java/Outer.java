public class Outer {

    private int ivar;

    // inner class
    private class Inner {

        private int num; // (could have an Inner() ctor)

        private void foo() {
            num++; // can access our regular inner ivar
            ivar = 13; // we can "see" our outer class automatically
            Outer.this.ivar = 13; // same as above
        }

        public String toString() {
            return "Beat Cal";
        }
    }

    public Object test() {
        ivar = 10;
        Inner in = new Inner();
        in.foo(); // can see things, even if private
        in.toString(); // call an Object method
        return in;

        // Return pointer to inner to our caller as Object.
        // They can call toString() on it.
    }
}