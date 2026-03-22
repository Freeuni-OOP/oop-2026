package mvc;

public class Controller {
    Model model;
    View view;

    Controller(Model m, View v) {
        model = m;
        view = v;
    }

    void setName(String name) {
        model.setName(name);
    }

    void updateView() {
        view.show(model.getName());
    }
}
