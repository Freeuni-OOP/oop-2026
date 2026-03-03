package aggregation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Teacher t1 = new Teacher("t1");
        Teacher t2 = new Teacher("t2");

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(t1);
        teachers.add(t2);

        School school = new School(teachers);
    }
}
