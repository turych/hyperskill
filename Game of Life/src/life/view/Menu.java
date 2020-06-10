package life.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private JButton play;
    private JButton pause;
    private JButton reset;
    private JSlider speed;

    private JTextField universeSize;

    private JTextField loops;

    private InfoPanel infoPanel;

    public Menu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        play = new JButton("play");
        play.setName("PlayToggleButton");
        add(play);

        pause = new JButton("pause");
        pause.setName("PauseButton");
        add(pause);

        reset = new JButton("stop");
        reset.setName("ResetButton");
        add(reset);

        JLabel speedLabel = new JLabel("Speed:");
        add(speedLabel);
        speed = new Speed(10, 1000, 100);
        speed.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(speed);

        JLabel universeLabel = new JLabel("Universe size:");
        add(universeLabel);

        universeSize = new JTextField("50");
        universeSize.setBounds(5, 5, 100, 50);
        add(universeSize);

        JLabel loopsLabel = new JLabel("Loop count:");
        add(loopsLabel);

        loops = new JTextField("50");
        loops.setBounds(5, 5, 100, 50);
        add(loops);

        infoPanel = new InfoPanel(0, 0);
        add(infoPanel);
        setVisible(true);

    }

    public void setGenerationLoop(int n) {
        infoPanel.setGenerationLoop(n);
    }

    public void setSpeedListener(ChangeListener l) {
        speed.addChangeListener(l);
    }

    public int getSpeed() {
        return speed.getValue();
    }

    public void setAliveCounter(int n) {
        infoPanel.setAliveCounter(+ n);
    }

    public void setPlayListener(ActionListener l) {
        play.addActionListener(l);
    }

    public void setPauseListener(ActionListener l) {
        pause.addActionListener(l);
    }

    public void setResetListener(ActionListener l) {
        reset.addActionListener(l);
    }

    public void disablePlayButton(boolean flag) {
        play.setEnabled(!flag);
    }

    public int getUniverseSize() {
        return Integer.parseInt(universeSize.getText());
    }

    public int getLoops() {
        return Integer.parseInt(loops.getText());
    }
}
