package mvp;

public class ConsoleView implements View {

    @Override
    public void show(String name) {
        System.out.println("User: " + name);
    }
}
