package life;

import java.util.Random;

public class Generation {

    private boolean[][] generation;

    private int alive = 0;

    public Generation(int size, int seed) {
        generate(size, seed);
    }

    public void generate(int size, int seed) {
        Random random = new Random(seed);
        generation = new boolean[size][size];
        boolean isAlive;
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                isAlive = random.nextBoolean();
                if (isAlive) alive++;
                generation[i][k] = isAlive;
            }
        }
    }

    public boolean[][] getGeneration() {
        return generation;
    }

    public int getAlive() {
        return alive;
    }
}
