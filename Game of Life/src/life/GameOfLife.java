package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private static final int UNIVERSE_SIZE = 100;
    private static final int LOOPS = 100;

    private static final int WINDOW_W = 300;
    private static final int WINDOW_H = 300;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_W, WINDOW_H);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WINDOW_W, WINDOW_H));
        setLayout(new BorderLayout());
        setVisible(true);

        start();
    }

    private void start() {
        LifeController controller = new LifeController(this);
    }

    public static void main(String[] args) {
        new GameOfLife();
    }
}
