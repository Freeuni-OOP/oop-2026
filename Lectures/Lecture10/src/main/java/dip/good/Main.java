package dip.good;

public class Main {

    public static void main(String[] args) {
        ReportService service = new ReportService(new FakeGenerator());
        service.generateReport();
    }
}
