package calculator;

public abstract class AbstractView implements DisplayListener {

    private InputListener inputListener; // controller

    public void registerInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public void fireInputChanged(String newInput) {
        this.inputListener.inputChanged(newInput);
    }

    public abstract void show();
}
