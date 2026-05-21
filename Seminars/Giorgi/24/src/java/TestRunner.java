import junit.BeforeEach;
import junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTests(Class<?>... tests) {
        for (Class<?> test : tests) {
            System.out.println("--------------Running test: " + test.getName() + "--------------");
            runTest(test);
        }
    }

    private static void runTest(Class<?> test) {
        List<Method> beforeEachMethods = getAnnotatedMethods(test, BeforeEach.class);
        List<Method> testMethods = getAnnotatedMethods(test, Test.class);

        int passed = 0, failed = 0;

        try {
            Object testInstance = test.newInstance();

            for (Method beforeEachMethod : beforeEachMethods) {
                beforeEachMethod.invoke(testInstance);
            }

            for (Method testMethod : testMethods) {
                try {
                    System.out.println("--- Running test method: " + testMethod.getName());
                    testMethod.invoke(testInstance);

                    passed++;

                    System.out.println("Passed: " + testMethod.getName());
                } catch (Exception e) {
                    failed++;

                    System.out.println("Failed: " + testMethod.getName());
                    System.out.println(e.getCause().getMessage());
                } finally {
                    System.out.println("-----------------------");
                }
            }

        } catch (Exception e) {
            System.out.println("Failed running test: " + test.getName());
        } finally {
            System.out.println("Total: " + (passed + failed) +
                    ", passed: " + passed +
                    ", failed: " + failed);
            System.out.println();
        }
    }

    private static List<Method> getAnnotatedMethods(Class<?> test, Class<? extends Annotation> annotation) {
        List<Method> result = new ArrayList<>();

        for (Method method : test.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                result.add(method);
            }
        }

        return result;
    }

    // java main math.MathTest
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?>[] classes = new Class<?>[args.length];

        for (int i = 0; i < args.length; i++) {
            classes[i] = Class.forName(args[i]);
        }

        runTests(classes);
    }
}
