public class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog d = (Dog) o;
            return name.equals(d.name);
        }
        return false;
    }
}
