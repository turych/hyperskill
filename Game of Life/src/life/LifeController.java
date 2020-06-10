package life;

import life.generation.Generation;
import life.generation.Universe;
import life.view.Menu;
import life.view.UniverseMap;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LifeController {

    private final int CELL_SIZE = 5;

    private JFrame mainWindow;
    private Menu menu;

    public Universe universe;

    private int universeSize;

    public int loops;

    public int speed = 10;

    private Life life;

    JPanel universePanel;

    public LifeController(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        initMenu();
    }

    public void startLife() {
        createUniverse();
        updateGUI();
        life = new Life(this);
        life.start();
    }

    private void createUniverse() {
        Generation generation = new Generation(universeSize, new Random().nextInt());
        universe = new Universe(generation);
        int w = menu.getWidth() + universeSize * CELL_SIZE;
        int h = universeSize * CELL_SIZE;
        mainWindow.setSize(w, h < menu.getHeight() ? menu.getHeight() : h);
    }

    private void initMenu() {
        menu = new Menu();
        menu.setSpeedListener(l -> changeSpeed());
        menu.setPlayListener(l -> {
            universeSize = menu.getUniverseSize();
            loops = menu.getLoops();
            startLife();
        });
        menu.setPauseListener(l -> {
            if (life.isLocked()) {
                life.unlock();
            } else {
                life.lock();
            }
        });

        menu.setResetListener(l -> {
            life.interrupt();
        });

        mainWindow.add(menu, BorderLayout.WEST);
        mainWindow.pack();
        universePanel = new JPanel();
        universePanel.setLayout(new GridLayout(1,1));
        mainWindow.add(universePanel, BorderLayout.CENTER);
    }

    private void changeSpeed() {
        speed = menu.getSpeed();
    }

    public void updateGUI() {
        universePanel.removeAll();
        UniverseMap universeMap = new UniverseMap(universe, CELL_SIZE);
        universePanel.add(universeMap);

        menu.setGenerationLoop(universe.getLoop());
        menu.setAliveCounter(universe.getAlive());

        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public void disablePlayButton(boolean flag) {
        menu.disablePlayButton(flag);
    }
}
