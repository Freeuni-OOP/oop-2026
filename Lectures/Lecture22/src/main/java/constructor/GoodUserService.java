package constructor;

class GoodUserService {
    private final Database db;

    GoodUserService(Database db) {
        this.db = db;
    }

    String getUser() {
        return db.findUser();
    }
}