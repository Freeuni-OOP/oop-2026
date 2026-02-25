public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("dog");
        Dog dog = new Dog("dog");

        System.out.println(animal.equals(dog));
        System.out.println(dog.equals(animal));
    }
}
