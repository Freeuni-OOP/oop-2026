package mvvm;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private String name;
    private final List<Runnable> listeners = new ArrayList<>();

    void setName(String name) {
        this.name = name;
        notifyListeners();
    }

    String getName() {
        return name;
    }

    void bind(Runnable listener) {
        listeners.add(listener);
    }

    void notifyListeners() {
        for (Runnable r : listeners) {
            r.run();
        }
    }
}
