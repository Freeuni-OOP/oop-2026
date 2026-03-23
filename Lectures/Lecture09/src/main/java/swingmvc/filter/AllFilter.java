package swingmvc.filter;

import swingmvc.table.Student;

public class AllFilter implements Filter {
    @Override
    public boolean filter(Student student) {
        return true;
    }
}
