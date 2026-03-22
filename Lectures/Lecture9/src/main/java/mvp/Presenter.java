package mvp;

public class Presenter {

    private final Model model;

    private final View view;

    Presenter(Model m, View v) {
        model = m;
        view = v;
    }

    void setName(String name) {
        model.setName(name);
        view.show(model.getName()); // directly updates view
    }
}
