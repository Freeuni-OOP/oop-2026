package mvp;

public class ConsoleView implements View {

    @Override
    public void showName(String name) {
        System.out.println("User: " + name);
    }

    @Override
    public void showError(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public void showInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void setSaveEnabled(boolean enabled) {
        System.out.println("Save enabled: " + enabled);
    }
}
