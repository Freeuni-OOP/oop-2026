import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class JavaFilesFinderStreams {

    public static void main(String[] args) {
        allFilesIn(new File("."))
                .filter(file -> file.getName().endsWith(".java"))
                .forEach(System.out::println);
    }

    static Stream<File> allFilesIn(File folder) {
        File[] children = folder.listFiles();

        if (children == null) {
            return Stream.empty();
        }

        Stream<File> files =
                Arrays.stream(children)
                        .filter(File::isFile);

        Stream<File> descendants =
                Arrays.stream(children)
                        .filter(File::isDirectory)
                        .flatMap(JavaFilesFinderStreams::allFilesIn);

        return Stream.concat(files, descendants);
    }
}