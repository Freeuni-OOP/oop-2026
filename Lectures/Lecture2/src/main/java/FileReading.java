import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Lectures/Lecture2/src/main/java/FileReading.java"));

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
