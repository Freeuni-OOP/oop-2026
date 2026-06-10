import bean.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final int port;
    private final String address;

    public Client(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start() {
        new Thread(() -> {
            try {
                Socket socket = new Socket(address, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                out.writeObject(Command.CREATE);
                out.writeObject(new Student(25, "a", "a"));
                System.out.println("[Client] Sent create command for student a a");

                out.writeObject(Command.CREATE);
                out.writeObject(new Student(26, "b", "b"));
                System.out.println("[Client] Sent create command for student b b");

                out.writeObject(Command.GET);
                out.writeObject(26);
                System.out.println("[Client] Sent GET command for student b b");

                Student student = (Student) in.readObject();
                System.out.println("[Client] Received student: " + student);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
