package swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Swing {
    private static int counter = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Counter");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton button = new JButton("Button");
        JLabel label = new JLabel("count: 0");

        button.addActionListener(e -> {
            counter++;
            button.setText("count: " + counter);
        });

        //        panel.add(label, BorderLayout.NORTH);
        panel.add(button, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}