package life.view;

import life.generation.Universe;

import javax.swing.*;
import java.awt.*;

public class UniverseMap extends JPanel {

    private int cellSize;

    private Universe universe;

    public UniverseMap(Universe universe, int cellSize) {
        this.universe = universe;
        this.cellSize = cellSize;
        Dimension dimension = new Dimension(universe.size() * cellSize, universe.size() * cellSize);
        setSize(dimension);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.DARK_GRAY);

        boolean[][] generation = universe.getGeneration();
        for (int y = 0; y < universe.size(); y++) {
            for (int x = 0; x < universe.size(); x++) {
                if (generation[y][x]) {
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
        repaint();
    }
}
