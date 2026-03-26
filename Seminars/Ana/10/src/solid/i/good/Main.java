package solid.i.good;

public class Main {
    public static void main(String[] args) {
        HumanWorker humanWorker = new HumanWorker();
        startLunch(humanWorker);

        Workable robotWorker = new RobotWorker();
        startWork(robotWorker);
    }

    public static void startWork(Workable worker) {
        worker.work();
    }

    public static void startLunch(Eatable worker) {
        worker.eat();
    }
}