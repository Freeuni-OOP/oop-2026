import controller.Controller;
import model.CalculatorBrain;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(), new CalculatorBrain());
        controller.start();
    }
}