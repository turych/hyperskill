package tictactoe;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    private static int[][] matrix;

    private static Player player = Player.X;

    private static int step = 0;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        matrix = new int[3][3];

        renderField();
        enterCoordinates();
    }

    private static void changePlayer() {
        player = player == Player.X ? Player.O : Player.X;
    }

    private static void renderField() {
        StringBuilder line = new StringBuilder();
        line.append("---------\n");
        for (int y = 2; y >= 0; y--) {
            line.append("| ");
            for (int x = 0; x < 3; x++) {
                if (matrix[x][y] == 0) {
                    line.append("  ");
                } else {
                    line.append((char) matrix[x][y]).append(" ");
                }
            }
            line.append("|\n");
        }
        line.append("---------");
        System.out.println(line.toString());
    }

    private static void enterCoordinates() {
        while (true) {
            System.out.println("Enter the coordinates:");

            int coorX = 0;
            int coorY = 0;

            String[] coors = scanner.nextLine().split(" ");
            try {
                coorX = Integer.parseInt(coors[0]);
                coorY = Integer.parseInt(coors[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coorX < 1 || coorX > 3 || coorY < 1 || coorY > 3){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            --coorX;
            --coorY;
            if (matrix[coorX][coorY] == 0) {
                matrix[coorX][coorY] = player.toString().charAt(0);
                renderField();
                if (!analyze()) {
                    break;
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private static boolean analyze() {

        step++;

        int winner = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == matrix[i][1]
                    && matrix[i][0] == matrix[i][2]) {

                if (matrix[i][0] != 0)
                    winner = player.toString().charAt(0);
            }
            if (matrix[0][i] == matrix[1][i]
                    && matrix[0][i] == matrix[2][i]) {

                if (matrix[0][i] != 0)
                    winner = player.toString().charAt(0);
            }
        }

        if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]
                || matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0]
        ) {
            if (matrix[1][1] != 0)
                winner = player.toString().charAt(0);
        }

        if (winner > 0) {
            System.out.println((char) winner + " wins");
            return false;
        } else if (step == 9) {
            System.out.println("Draw");
            return false;
        } else {
            changePlayer();
            return true;
        }
    }
}

enum Player {
    X,
    O
}
