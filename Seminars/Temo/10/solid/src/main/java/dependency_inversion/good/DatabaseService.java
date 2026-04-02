package dependency_inversion.good;

public class DatabaseService {

  // low level
  private Database database;

  public DatabaseService(Database database) {
    this.database = database;
  }

  public void saveUser(String user) {
    database.saveUser(user);
  }
}
