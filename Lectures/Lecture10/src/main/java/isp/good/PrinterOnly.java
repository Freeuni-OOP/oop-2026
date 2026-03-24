package isp.good;

public class PrinterOnly implements Printer {
    @Override
    public void print() {
        System.out.println("printing...");
    }
}
