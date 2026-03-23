public class Parrot extends Bird {

    public Parrot(String name) {
        super(name); // important
    }

    @Override
    public String getName() {
        return "parrot";
    }
}
