package bean;

public class Course {
    private Long id;
    private Long course_id;
    private String course_name;
    private int course_credit;
    private String course_type;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", course_credit=" + course_credit +
                ", course_type='" + course_type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public String getCourse_type() {
        return course_type;
    }

    public Course(Long id,
                  Long course_id,
                  String course_name,
                  int course_credit,
                  String course_type) {
        this.id = id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_credit = course_credit;
        this.course_type = course_type;
    }
}
