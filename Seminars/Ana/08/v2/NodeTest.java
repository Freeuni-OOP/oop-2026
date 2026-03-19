package v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NodeTest {

    @Test
    public void testValue() {
        Node valueNode = new NumberNode(5);
        assertEquals(5.0, valueNode.evaluate());
        assertEquals("5.0", valueNode.toString());
    }

    @Test
    public void testPlus() {
        Node plusNode = new AddNode(new NumberNode(1), new NumberNode(2));
        assertEquals(3.0, plusNode.evaluate());
        assertEquals("(1.0+2.0)", plusNode.toString());
    }

    @Test
    public void testDivide() {
        v1.Node v2 = new v1.Node('*', 2, 3);
        v1.Node v1 = new v1.Node('+', 1, 2);
        v1.Node v3 = new v1.Node('/', v2, v1);
        assertEquals(2, v3.evaluate());
        assertEquals("((2.0*3.0)/(1.0+2.0))", v3.toString());
    }

    @Test
    public void testIllegalOperator() {
        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                v1.Node node = new v1.Node('%', 1, 2);
                node.evaluate();
            }
        });
    }

    //    @Test
    //    public void testIllegalOperatorToString() {
    //        assertThrows(RuntimeException.class, new Executable() {
    //            @Override
    //            public void execute() throws Throwable {
    //                v1.Node node = new Node('%', 1, 2);
    //                node.toString();
    //            }
    //        });
    //    }
}