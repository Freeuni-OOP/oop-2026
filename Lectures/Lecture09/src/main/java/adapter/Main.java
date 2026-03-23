package adapter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300, 500);
        frame.addWindowListener(new Terminator());
        frame.setVisible(true);
    }
}
