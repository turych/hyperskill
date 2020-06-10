package life;

public class Universe {

    private boolean[][] generation;

    private int alive;

    private int loop = 0;

    public Universe(Generation generation) {
        this.generation = generation.getGeneration();
        this.alive = generation.getAlive();
    }

    public boolean[][] getGeneration() {
        return generation;
    }

    public int getAlive() {
        return alive;
    }

    public int getLoop() {
        return loop;
    }

    /**
     * An alive cell survives if has two or three alive neighbors;
     * otherwise, it dies of boredom (<2) or overpopulation (>3).
     * <p>
     * A dead cell is reborn if it has exactly three alive neighbors
     */
    public void doLoop() {
        boolean[][] newG = new boolean[generation.length][generation.length];
        int neighbors;
        for (int y = 0; y < generation.length; y++) {
            for (int x = 0; x < generation.length; x++) {
                neighbors = neighbors(x, y);
                newG[y][x] = generation[y][x];
                if (generation[y][x] && (neighbors < 2 || neighbors > 3)) {
                    newG[y][x] = false;
                    alive--;
                } else if (!generation[y][x] && neighbors == 3) {
                    newG[y][x] = true;
                    alive++;
                }
            }
        }
        generation = newG;
        loop++;
    }

    private int neighbors(int cellX, int cellY) {
        int size = generation.length;
        int lastCell = size - 1;
        int n = 0;
        int x;
        int y;
        for (int ny = -1; ny < 2; ny++) {
            for (int nx = -1; nx < 2; nx++) {
                x = cellX + nx < 0 ? lastCell : cellX + nx == size ? 0 : cellX + nx;
                y = cellY + ny < 0 ? lastCell : cellY + ny == size ? 0 : cellY + ny;
                if (generation[y][x] && !(cellX == x && cellY == y)) {
                    n++;
                }
            }
        }
        return n;
    }
}
