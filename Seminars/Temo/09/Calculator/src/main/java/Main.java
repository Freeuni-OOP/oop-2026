import model.CalculatorBrain;
import view.AbstractView;
import view.SwingView;

public class Main {
  public static void main(String[] args) {
    CalculatorBrain calculatorBrain = new CalculatorBrain();
    AbstractView view = new SwingView();
    Controller controller = new Controller(calculatorBrain, view);
    view.setInputListener(controller);
    calculatorBrain.setDisplayListener(controller);
    view.start();
  }

}
