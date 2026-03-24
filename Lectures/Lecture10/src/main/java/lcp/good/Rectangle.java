package lcp.good;

class Rectangle implements Shape {

    private final int width;

    private final int height;

    public Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int getArea() {
        return width * height;
    }
}