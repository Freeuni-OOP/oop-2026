import javax.swing.*;
import java.awt.*;

public class SwingExample {

    private static class MyButton extends JButton {
        public MyButton(String text) {
            super(text);
            setForeground(Color.CYAN);
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawOval(5, 5, this.getWidth() - 10, this.getHeight() - 10);
        }
    }
}
