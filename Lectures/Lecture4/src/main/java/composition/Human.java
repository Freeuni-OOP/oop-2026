package composition;

public class Human {

    private Heart heart;

    public Human() {
        heart = new Heart();
    }

    public Heart getHeart() {
        return heart;
    }

    public void setHeart(Heart heart) {
        this.heart = heart;
    }
}
