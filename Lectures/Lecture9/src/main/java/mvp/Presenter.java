package mvp;

public class Presenter {

    private static final int MIN_NAME_LENGTH = 3;

    private final Model model;

    private final View view;

    Presenter(Model m, View v) {
        model = m;
        view = v;
    }

    void update(String rawName) {
        String name = rawName == null ? "" : rawName.trim().replaceAll("\\s+", " ");

        if (name.length() < MIN_NAME_LENGTH) {
            view.showError("Name must contain at least " + MIN_NAME_LENGTH + " letters.");
            view.setSaveEnabled(false);
            return;
        }

        if (name.equals(model.getName())) {
            view.showInfo("No changes to save.");
            view.setSaveEnabled(true);
            return;
        }

        model.setName(name);
        view.showName(name);
        view.showInfo("Saved by presenter.");
        view.setSaveEnabled(true);
    }
}
