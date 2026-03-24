package dip.bad;

public class ReportService {
    private final PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();

    public void generate() {
        pdfReportGenerator.generatePdf();
    }
}

