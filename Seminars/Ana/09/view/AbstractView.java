package view;

public abstract class AbstractView implements DisplayListener {
    private InputListener inputListener;

    public abstract void show();

    public void registerInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public void fireInputChanged(String input) {
        inputListener.inputChanged(input);
    }
}