package interface_segregation.bad;

public class RobotWorker implements Worker {

  @Override
  public void work() {
    //
  }

  @Override
  public void eat() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
