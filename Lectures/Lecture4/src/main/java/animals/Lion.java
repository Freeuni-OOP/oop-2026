package animals;

import zoo.Animal;

public class Lion extends Animal {

    protected void makeSound() {
        sound(); // ok due to protected access
    }
}
