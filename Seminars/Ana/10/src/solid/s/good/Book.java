package solid.s.good;

public class Book {
    private String name;
    private String content;
    private int year;

    public Book(String name, String content, int year) {
        this.name = name;
        this.content = content;
        this.year = year;
    }

    public Book() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}