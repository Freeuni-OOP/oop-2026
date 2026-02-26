public class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    // just remove this equals()
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog a = (Dog) o;
            return name.equals(a.name);
        }
        return false;
    }
}
