class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Animal) {
            Animal a = (Animal) o;
            return name.equals(a.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}