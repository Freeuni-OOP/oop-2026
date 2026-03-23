package binky;

public class BinkyClient {

    // bad client side code
    private int computeSum(Binky binky) {
        int sum = 0;
        for (int i = 0; i < binky.length; i++) { // NO -- reaching in
            sum += binky.data[i]; // NO -- reaching in
        }
        return sum;
    }

    // better but still bad client side code
    private int computeSumV2(Binky binky) {
        int sum = 0;
        for (int i = 0; i < binky.getLength(); i++) { // NO -- external entity doing
            sum += binky.getItem(i); // too much work on object's data
        }
        return sum;
    }
}
