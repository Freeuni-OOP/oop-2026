package isp.bad;

public class PrinterOnly implements Machine {
    public void print() {
        System.out.println("printing...");
    }

    public void scan() {
        throw new UnsupportedOperationException();
    }

    public void fax() {
        throw new UnsupportedOperationException();
    }
}