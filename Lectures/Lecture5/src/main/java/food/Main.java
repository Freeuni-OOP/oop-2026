package food;

public class Main {
    public static void main(String[] args) {
        Candy candy = new Candy();

        eat(candy);
    }

    public static void eat(Food food) {
        Food broccoli = new Broccoli();

        if (food.same(broccoli)) {
            System.out.println("broccoli!");
        }
    }
}
