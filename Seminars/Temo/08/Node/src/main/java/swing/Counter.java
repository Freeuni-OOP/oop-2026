package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Counter {

  private int count = 0;

  public Counter() {
    JFrame frame = new JFrame("Counter");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLayout(new BorderLayout());

    JLabel label = new JLabel("Count: 0", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));

    JButton button = new JButton("Increment");

    button.addActionListener(e -> {
      count++;
      label.setText("Count: " + count);
    });

    frame.add(label, BorderLayout.CENTER);
    frame.add(button, BorderLayout.SOUTH);

    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(Counter::new);
  }
}