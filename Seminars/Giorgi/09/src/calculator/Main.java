package calculator;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new SwingView(), new CalculatorBrain());

        controller.startApp();
    }
}
