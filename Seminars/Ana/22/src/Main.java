import dao.StudentDAO;
import dao.StudentDAOImpl;

public class Main {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        Client client = new Client(8080, "localhost");
        Server server = new Server(8080, studentDAO);

        client.start();
        server.start();
    }
}