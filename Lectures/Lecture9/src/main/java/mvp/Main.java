package mvp;

public class Main {

    public static void main(String[] args) {
        Model m = new Model();
        View v = new ConsoleView();
        Presenter p = new Presenter(m, v);

        p.setName("Giorgi");
    }
}
