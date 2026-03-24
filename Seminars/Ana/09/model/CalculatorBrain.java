package model;

import view.DisplayListener;

public class CalculatorBrain {
    private DisplayListener displayListener;
    private String prevInput;
    private String curInput;
    private char operator;

    public CalculatorBrain() {
        prevInput = "0";
        curInput = "0";
        operator = '$';
    }

    public void addNumber(String number) {
        curInput = number;
        if (operator == '$') {
            fireDisplayChanged(number);
        } else {
            double result = calculate();
            operator = '$';
            prevInput = "0";
            curInput = "0";
            fireDisplayChanged(String.valueOf(result));
        }
    }

    private double calculate() {
        double num1 = Double.parseDouble(prevInput);
        double num2 = Double.parseDouble(curInput);

        switch (operator) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            return num1 / num2;
        }
        return 0;
    }

    public void fireDisplayChanged(String newValue) {
        displayListener.displayChanged(newValue);
    }

    public void addOperator(char operator) {
        this.operator = operator;
        prevInput = curInput;
        fireDisplayChanged("" + operator);
    }

    public void registerDisplayListener(DisplayListener displayListener) {
        this.displayListener = displayListener;
    }
}