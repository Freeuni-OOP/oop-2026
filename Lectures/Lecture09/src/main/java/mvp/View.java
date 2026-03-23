package mvp;

public interface View {
    void showName(String name);

    void showError(String message);

    void showInfo(String message);

    void setSaveEnabled(boolean enabled);
}
