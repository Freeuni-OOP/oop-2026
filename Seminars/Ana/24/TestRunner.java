import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        runTests(MathTest.class);
    }

    public static void runTests(Class<?> testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object testInstance = testClass.getDeclaredConstructor().newInstance();
        Method[] methods = testClass.getDeclaredMethods();

        Method beforeEach = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                beforeEach = method;
                break;
            }
        }

        int passed = 0;
        int failed = 0;

        for (Method testMethod : methods) {
            if (testMethod.isAnnotationPresent(Test.class)) {
                if (beforeEach != null) {
                    beforeEach.invoke(testInstance);
                }

                try {
                    testMethod.invoke(testInstance);
                    System.out.println("Test passed: " + testMethod.getName());
                    passed++;
                } catch (InvocationTargetException e) {
                    System.out.println("Test failed: " + testMethod.getName() + " -> " + e.getCause().getMessage());
                    failed++;
                }
            }
        }
        System.out.println("passed: " + passed + ", failed: " + failed);
    }
}
