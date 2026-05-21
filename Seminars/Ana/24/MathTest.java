public class MathTest {
    private static Math math;

    @BeforeEach
    public void setUp() {
        math = new Math();
        System.out.println("MathTest setUp");
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(5, math.add(2, 3));
    }

    @Test()
    public void testSubtract() {
        Assert.assertEquals(1, math.subtract(3, 2));
    }
}
