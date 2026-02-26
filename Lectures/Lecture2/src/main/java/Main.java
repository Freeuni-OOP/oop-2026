public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("pudeli");
        Dog dog = new Dog("pudeli");

        // symmetry violation
        System.out.println(animal.equals(dog)); // true
        System.out.println(dog.equals(animal)); // false !!!
    }
}
