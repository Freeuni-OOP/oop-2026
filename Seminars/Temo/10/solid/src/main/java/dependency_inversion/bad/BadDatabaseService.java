package dependency_inversion.bad;

public class BadDatabaseService {

  // low level
  private BadMySQLDatabase badMySQLDatabase;

  public BadDatabaseService(BadMySQLDatabase badMySQLDatabase) {
    this.badMySQLDatabase = badMySQLDatabase;
  }

  public void saveUser(String user) {
    badMySQLDatabase.saveUser(user);
  }
}