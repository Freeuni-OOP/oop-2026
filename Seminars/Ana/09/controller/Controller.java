package controller;

import model.CalculatorBrain;
import view.AbstractView;
import view.InputListener;

public class Controller implements InputListener {
    private AbstractView view;
    private CalculatorBrain brain;

    public Controller(AbstractView view, CalculatorBrain brain) {
        this.view = view;
        this.brain = brain;

        this.view.registerInputListener(this);
        this.brain.registerDisplayListener(view);
    }

    @Override
    public void inputChanged(String input) {
        char ch = input.charAt(0);
        if (Character.isDigit(ch)) {
            brain.addNumber(input);
        } else if ("+-*/".indexOf(ch) != -1) {
            brain.addOperator(ch);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public void start() {
        view.show();
    }
}