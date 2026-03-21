package mvc.filter;

import mvc.table.Student;

public class AllFilter implements Filter {
    @Override
    public boolean filter(Student student) {
        return true;
    }
}
