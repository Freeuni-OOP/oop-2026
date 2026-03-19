package v3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NodeTest {

  @Test
  public void testValue() {
    Node valueNode = new Number(1);
    assertEquals(1.0, valueNode.evaluate());
    assertEquals("1.0", valueNode.toString());
  }

  @Test
  public void testPlus() {
    Node v1 = new Add(new Number(1),  new Number(2));
    Node v2 = new Multiply(new Number(2),  new Number(3));
    Node v3 = new Subtract(v1, v2);
    assertEquals(-3, v3.evaluate());
    assertEquals("((1.0+2.0)-(2.0*3.0))", v3.toString());
  }

  @Test
  public void testDivide() {
    Node v2 = new Multiply(new Number(2), new Number(3));
    Node v1 = new Add(new Number(1), new Number(2));
    Node v3 = new Divide(v2, v1);
    assertEquals(2, v3.evaluate());
    assertEquals("((2.0*3.0)/(1.0+2.0))", v3.toString());
  }
}
