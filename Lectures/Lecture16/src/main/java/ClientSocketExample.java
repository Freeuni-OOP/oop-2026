import java.io.*;
import java.net.*;

public class ClientSocketExample {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        // send to server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello server!");

        // read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String response = in.readLine();
        System.out.println("Server says: " + response);

        socket.close();
    }
}