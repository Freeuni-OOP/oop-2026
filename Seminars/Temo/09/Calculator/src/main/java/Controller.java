import model.CalculatorBrain;
import view.AbstractView;
import view.DisplayListener;
import view.InputListener;

public class Controller implements InputListener, DisplayListener {

  private final CalculatorBrain brain;
  private final AbstractView view;

  public Controller(CalculatorBrain brain, AbstractView view) {
    this.brain = brain;
    this.view = view;
  }

  @Override
  public void onInput(String input) {
    char currentChar = input.charAt(0);

    if (Character.isDigit(currentChar)) {
      brain.onDigitClicked(currentChar);
      return;
    }

    if (currentChar == '=') {
      brain.onEqualsClicked();
      return;
    }

    brain.onOperatorClicked(currentChar);
  }

  @Override
  public void onDisplay(String input) {
    view.onDisplay(input);
  }
}
