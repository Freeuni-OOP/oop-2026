package mvvm;

public class View {
    View(ViewModel vm) {
        vm.bind(() -> {
            System.out.println("User: " + vm.getName());
        });
    }
}
