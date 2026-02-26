public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("pudeli");
        Dog dog = new Dog("pudeli");

        System.out.println(animal.equals(dog));
        System.out.println(dog.equals(animal));
    }
}
