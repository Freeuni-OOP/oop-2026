package aggregation;

import java.util.List;

public class School {
    private final List<Teacher> teachers;

    public School(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
