package calculator;

public class CalculatorBrain {

    private DisplayListener displayListener;

    private char operator;

    private String prevDisplay;

    private String currDisplay;

    public CalculatorBrain() {
        this.prevDisplay = "0";
        this.currDisplay = "0";
        this.operator = '$';
    }

    public void registerDisplayListener(DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    void addNumber(String number) {
        this.currDisplay = number;

        if (operator == '$') {
            fireDisplayChanged(currDisplay);
        } else {
            calculate();
        }
    }

    void addOperator(char operator) {
        this.operator = operator;
        this.prevDisplay = currDisplay;
        fireDisplayChanged("" + operator);
    }

    private void calculate() {
        double prev = Double.parseDouble(prevDisplay);
        double curr = Double.parseDouble(currDisplay);

        double result = 0;

        switch (operator) {
            case '+':
                result = prev + curr;
                break;
            case '-':
                result = prev - curr;
                break;
            case '*':
                result = prev * curr;
                break;
            case '/':
                result = prev / curr;
                break;
        }

        this.currDisplay = "" + result;
        this.operator = '$';
        fireDisplayChanged(currDisplay);
    }

    private void fireDisplayChanged(String newDisplay) {
        displayListener.displayChanged(newDisplay);
    }
}
