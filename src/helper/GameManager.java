package helper;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameManager {
    public static JFrame f;

    public static void switchTo(JPanel panel) {
        f.setContentPane(panel);
        f.revalidate();
        f.repaint();
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
    }
}

