package model;

import view.DisplayListener;

public class CalculatorBrain {

  private int result = 0;
  private final StringBuilder currentNumber = new StringBuilder("0");
  private char operator = ' ';
  private char previousChar = ' ';
  private boolean hasResult = false;

  private DisplayListener displayListener;

  public void setDisplayListener(DisplayListener displayListener) {
    this.displayListener = displayListener;
  }

  public void fireDisplayChanged(String input) {
    displayListener.onDisplay(input);
  }

  public void onDigitClicked(char digit) {
    if (!Character.isDigit(previousChar)) {
      currentNumber.setLength(0);
    }

    currentNumber.append(digit);
    previousChar = digit;
    fireDisplayChanged(currentNumber.toString());
  }

  public void onOperatorClicked(char nextOperator) {
    if (!isOperator(nextOperator)) {
      fireDisplayChanged("Error: Invalid input");
      return;
    }

    if (previousChar == '=' && hasResult) {
      operator = nextOperator;
      previousChar = nextOperator;
      fireDisplayChanged(String.valueOf(result));
      return;
    }

    if (isOperator(previousChar)) {
      operator = nextOperator;
      previousChar = nextOperator;
      fireDisplayChanged(String.valueOf(result));
      return;
    }

    if (!Character.isDigit(previousChar) || currentNumber.length() == 0) {
      fireDisplayChanged("Error: Invalid input");
      return;
    }

    if (!applyCurrentNumber()) {
      return;
    }

    operator = nextOperator;
    previousChar = nextOperator;
    fireDisplayChanged(String.valueOf(result));
  }

  public void onEqualsClicked() {
    if (!applyCurrentNumber()) {
      return;
    }

    operator = ' ';
    previousChar = '=';
    fireDisplayChanged(String.valueOf(result));
  }

  private boolean applyCurrentNumber() {
    String numberToken = currentNumber.toString();
    int number;

    try {
      number = Integer.parseInt(numberToken);
    } catch (NumberFormatException e) {
      fireDisplayChanged("Error: Invalid input");
      return false;
    }

    if (!hasResult || operator == ' ') {
      result = number;
      hasResult = true;
      currentNumber.setLength(0);
      return true;
    }

    switch (operator) {
      case '+':
        result += number;
        break;
      case '-':
        result -= number;
        break;
      case '*':
        result *= number;
        break;
      case '/':
        if (number == 0) {
          fireDisplayChanged("Error: Division by zero");
          return false;
        }
        result /= number;
        break;
      default:
        fireDisplayChanged("Error: Invalid input");
        return false;
    }

    currentNumber.setLength(0);
    return true;
  }

  private boolean isOperator(char character) {
    return character == '+' || character == '-' || character == '*' || character == '/';
  }

}
