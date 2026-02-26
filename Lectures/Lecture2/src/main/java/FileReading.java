import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {

    public static void main(String[] args) {
        // try - with -resources
        try (BufferedReader reader = new BufferedReader(new FileReader("Lectures/Lecture2/src/main/java/FileReading.java"))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
