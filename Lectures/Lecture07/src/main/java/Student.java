public class Student implements Moodable {

    public static void main(String[] args) {
        Moodable student = new Student();

        // System.out.println(student.getStress()); compiler error
        System.out.println(student.getMood());
    }

    @Override
    public String getMood() {
        return "moooooooood";
    }

    public String getStress() {
        return "I'm stressed!";
    }
}
