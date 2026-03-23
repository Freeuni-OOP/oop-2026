package calculator;

public class Controller implements InputListener {

    private final AbstractView view;

    private final CalculatorBrain brain;

    public Controller(AbstractView view, CalculatorBrain brain) {
        this.view = view;
        this.brain = brain;

        this.view.registerInputListener(this); // controller listens view
        this.brain.registerDisplayListener(view); // view listens to brain
    }

    @Override
    public void inputChanged(String newInput) {
        char firstChar = newInput.charAt(0);

        if (Character.isDigit(firstChar)) {
            brain.addNumber(newInput);
        } else if ("+-*/".indexOf(firstChar) != -1) { // +-*/
            brain.addOperator(firstChar);
        }
    }

    public void startApp() {
        view.show();
    }
}
