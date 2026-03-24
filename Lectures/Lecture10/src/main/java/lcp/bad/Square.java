package lcp.bad;

public class Square extends Rectangle {

    int side;

    public Square(int side) {
        super(side, side);
        this.side = side;
    }

    public int getArea() {
        return side * side;
    }
}
