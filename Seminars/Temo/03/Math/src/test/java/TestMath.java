import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestMath {

  @Test
  public void TestAddPositiveNumbers() {
    // 2 3
    // 3 5
    // 8 9
    assertEquals(5, Math.add(2, 3));
    assertEquals(8, Math.add(3, 5));
    assertEquals(17, Math.add(8, 9));
  }

  @Test
  public void TestAddNegativeNumbers() {
    assertEquals(-6, Math.add(-2, -4));
    assertEquals(-8, Math.add(-3, -5));
    assertEquals(-17, Math.add(-8, -9));
  }

  @Test
  public void TestAddNegativeToPositive() {
    assertEquals(1, Math.add(-2, 3));
    assertEquals(-100, Math.add(-1000, 900));
    assertEquals(0, Math.add(-1003, 1003));
  }

  @Test
  public void TestAddZero() {
    assertEquals(0, Math.add(0, 0));
    assertEquals(1, Math.add(0, 1));
    assertEquals(-870, Math.add(0, -870));
  }

  @Test
  public void TestAbsPositiveNumbers() {
    assertEquals(5, Math.abs(5));
    assertEquals(8, Math.abs(8));
    assertEquals(Integer.MAX_VALUE, Math.abs(Integer.MAX_VALUE));
  }

  @Test
  public void TestAbsNegativeNumbers() {
    assertEquals(5, Math.abs(-5));
    assertEquals(8, Math.abs(-8));
    assertEquals(Integer.MAX_VALUE, Math.abs(Integer.MIN_VALUE + 1));
  }

  @Test
  public void TestAbsZero() {
    assertEquals(0, Math.abs(0));
  }
}
