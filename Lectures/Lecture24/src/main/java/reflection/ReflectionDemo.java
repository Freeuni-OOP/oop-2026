package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("reflection.Person");

        System.out.println("===========================================");
        System.out.println(" CLASS INFO");
        System.out.println("===========================================");
        System.out.println("Class name     : " + clazz.getName());
        System.out.println("Simple name    : " + clazz.getSimpleName());
        System.out.println("Package        : " + clazz.getPackage().getName());
        System.out.println("Superclass     : " + clazz.getSuperclass().getName());

        System.out.println("\n===========================================");
        System.out.println(" CONSTRUCTORS");
        System.out.println("===========================================");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("Constructor: " + constructor);
        }

        System.out.println("\n===========================================");
        System.out.println(" FIELDS");
        System.out.println("===========================================");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("%-12s | %-10s | %s%n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());
        }

        System.out.println("\n===========================================");
        System.out.println(" METHODS");
        System.out.println("===========================================");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.printf("%-20s | returns: %-10s | params: %d%n",
                    Modifier.toString(method.getModifiers()) + " " + method.getName(),
                    method.getReturnType().getSimpleName(),
                    method.getParameterCount());
        }

        System.out.println("\n===========================================");
        System.out.println(" CREATE INSTANCE VIA REFLECTION");
        System.out.println("===========================================");
        Constructor<?> twoArgConstructor = clazz.getDeclaredConstructor(String.class, int.class);
        Object person = twoArgConstructor.newInstance("Alice", 30);
        System.out.println("Created: " + person);

        System.out.println("\n===========================================");
        System.out.println(" READ PRIVATE FIELD");
        System.out.println("===========================================");
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);  // bypass private!
        int age = (int) ageField.get(person);
        System.out.println("Private field 'age' = " + age);

        System.out.println("\n===========================================");
        System.out.println(" CALL PRIVATE METHOD");
        System.out.println("===========================================");
        Method getAgeMethod = clazz.getDeclaredMethod("getAge");
        getAgeMethod.setAccessible(true);  // bypass private!
        int result = (int) getAgeMethod.invoke(person);
        System.out.println("Private method getAge() returned: " + result);

        System.out.println("\n===========================================");
        System.out.println(" MODIFY PRIVATE FIELD");
        System.out.println("===========================================");
        ageField.set(person, 99);
        System.out.println("After setting age to 99: " + person);
    }
}

