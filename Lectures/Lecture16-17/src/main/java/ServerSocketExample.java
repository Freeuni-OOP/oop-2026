import java.io.*;
import java.net.*;

public class ServerSocketExample {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started on port 5000...");

        // wait for client
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        // read from client
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // send to client
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String message = in.readLine();
        System.out.println("Client says: " + message);

        out.println("Hello from server!");

        socket.close();
        serverSocket.close();
    }
}