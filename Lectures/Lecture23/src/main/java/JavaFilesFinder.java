import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFilesFinder {

    public static void main(String[] args) {
        File root = new File(".");
        List<File> allFiles = findAllFiles(root);
        List<File> javaFiles = filterJavaFiles(allFiles);
        printFiles(javaFiles);
    }

    static List<File> findAllFiles(File folder) {
        List<File> result = new ArrayList<>();

        File[] children = folder.listFiles();
        if (children == null) {
            return result;
        }

        for (File child : children) {
            if (child.isDirectory()) {
                List<File> descendants = findAllFiles(child);
                addAll(result, descendants);
            } else if (child.isFile()) {
                result.add(child);
            }
        }

        return result;
    }

    static List<File> filterJavaFiles(List<File> files) {
        List<File> javaFiles = new ArrayList<>();

        for (File file : files) {
            if (isJavaFile(file)) {
                javaFiles.add(file);
            }
        }

        return javaFiles;
    }

    static boolean isJavaFile(File file) {
        return file.getName().endsWith(".java");
    }

    static void addAll(List<File> target,
                       List<File> source) {
        target.addAll(source);
    }

    static void printFiles(List<File> files) {
        for (File file : files) {
            System.out.println(file);
        }
    }
}