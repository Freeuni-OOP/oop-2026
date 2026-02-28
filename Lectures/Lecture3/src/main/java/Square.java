public class Square {

    private final int width;

    public Square(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public int getArea() {
        return this.width * this.width;
    }
}
