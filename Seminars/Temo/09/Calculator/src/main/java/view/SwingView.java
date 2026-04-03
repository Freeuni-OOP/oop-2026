package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SwingView extends AbstractView implements DisplayListener {

  private JFrame frame;
  private JTextField displayField;

  @Override
  public void start() {
    SwingUtilities.invokeLater(() -> {
      frame = new JFrame("Calculator");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout(12, 12));

      displayField = new JTextField("0");
      displayField.setEditable(false);
      displayField.setHorizontalAlignment(JTextField.RIGHT);
      displayField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));

      JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 8, 8));
      String[] labels = {
          "7", "8", "9", "/",
          "4", "5", "6", "*",
          "1", "2", "3", "-",
          "0", "+", "="
      };

      for (String label : labels) {
        buttonPanel.add(createButton(label));
      }

      frame.add(displayField, BorderLayout.NORTH);
      frame.add(buttonPanel, BorderLayout.CENTER);
      frame.setSize(320, 420);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }

  @Override
  public void onDisplay(String input) {
    if (displayField == null) {
      return;
    }
    SwingUtilities.invokeLater(() -> displayField.setText(input));
  }

  private JButton createButton(String label) {
    JButton button = new JButton(label);
    button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
    button.addActionListener(event -> fireInputChanged(label));
    return button;
  }
}
