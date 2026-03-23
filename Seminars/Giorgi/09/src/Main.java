import calculator.*;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(), new CalculatorBrain());

        controller.startApp();
    }
}
