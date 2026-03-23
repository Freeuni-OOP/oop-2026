package mvvm;

public class Main {
    public static void main(String[] args) {
        ViewModel vm = new ViewModel();
        View v = new View(vm);

        vm.setName("Giorgi"); // auto updates view
    }
}
