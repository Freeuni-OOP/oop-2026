package solid.i.good;

public class HumanWorker implements Workable, Eatable {
    @Override
    public void eat() {
        System.out.println("human eating");
    }

    @Override
    public void work() {
        System.out.println("human working");
    }
}