
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

            JFrame window = new JFrame();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Bomberman");

            GamePanel gamePanel = new GamePanel();

            window.add(gamePanel);

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();
        }
    }
