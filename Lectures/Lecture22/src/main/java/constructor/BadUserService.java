package constructor;

class BadUserService {
    private final Database db;

    BadUserService() {
        db = new Database(); // hardcoded dependency
    }

    String getUser() {
        return db.findUser();
    }
}