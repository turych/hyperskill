package life.view;

import javax.swing.*;

public class InfoPanel extends JPanel {

    private JLabel generationLoop;

    private JLabel aliveCounter;

    public InfoPanel(int loop, int alive) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);

        this.generationLoop = new JLabel();
        generationLoop.setName("GenerationLabel");
        add(generationLoop);
        setGenerationLoop(loop);

        this.aliveCounter = new JLabel();
        aliveCounter.setName("AliveLabel");
        add(aliveCounter);
        setAliveCounter(alive);
    }

    public void setGenerationLoop(int n) {
        this.generationLoop.setText("Generation #" + n);
    }

    public void setAliveCounter(int n) {
        this.aliveCounter.setText("Alive: " + n);
    }
}
