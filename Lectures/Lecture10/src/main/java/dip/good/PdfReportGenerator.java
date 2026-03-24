package dip.good;

public class PdfReportGenerator implements ReportGenerator {
    @Override
    public void generate() {
        System.out.println("generating pdf...");
    }
}
