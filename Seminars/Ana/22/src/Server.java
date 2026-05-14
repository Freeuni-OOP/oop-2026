import bean.Student;
import dao.StudentDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    private final StudentDAO dao;

    public Server(int port, StudentDAO dao) {
        this.port = port;
        this.dao = dao;
    }

    public void start() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("[Server] Server connected on port " + port);
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Server] Client connected on port " + port);

                processCommand(clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void processCommand(Socket socket) {
        new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    Command command = (Command) in.readObject();
                    System.out.println("[Server] Command: " + command);
                    switch (command) {
                    case CREATE:
                        Student student = (Student) in.readObject();
                        dao.addStudent(student);
                        break;
                    case GET:
                        int id = (int) in.readObject();
                        Student student1 = dao.getStudentById(id);
                        out.writeObject(student1 == null ? "Student not found" : student1);
                        break;
                    default:
                        System.out.println("[Server] Unknown command: " + command);
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("[Server] Connection closed");
                throw new RuntimeException(e);
            }
        }).start();
    }
}
