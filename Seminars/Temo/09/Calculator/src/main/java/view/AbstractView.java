package view;

public abstract class AbstractView implements DisplayListener {
  private InputListener inputListener;

  public void setInputListener(InputListener inputListener) {
    this.inputListener = inputListener;
  }

  public void fireInputChanged(String input) {
    inputListener.onInput(input);
  }

  public abstract void start();
}
