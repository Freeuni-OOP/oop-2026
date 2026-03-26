package dip.good;

public class FakeGenerator implements ReportGenerator{
    @Override
    public void generate() {
        System.out.println("mock implementation");
    }
}
