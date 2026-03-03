package binky;

public class Binky {

    int[] data;

    int length;

    // great code! push the code to the data!
    public int sum() {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += data[i];
        }
        return sum;
    }

    public int getLength() {
        return length;
    }

    public int getItem(int index) {
        return data[index];
    }
}
