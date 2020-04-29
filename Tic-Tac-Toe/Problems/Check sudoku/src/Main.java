import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int size = (int) Math.pow(N, 2);
        int sumOfNumbers = sumOfNumbers(size);

        int[][] sudoku = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                sudoku[i][k] = scanner.nextInt();
            }
        }

        int _sum = 0;
        for (int i = 0; i < size; i += N) {
            for (int k = 0; k < size; k += N) {
                for (int j = i; j < N + i; j++) {
                    for (int l = k; l < N + k; l++) {
                        _sum += sudoku[j][l];
                    }
                }
                if (_sum != sumOfNumbers) {
                    System.out.println("NO");
                    return;
                }
                _sum = 0;
            }
        }

        int _sum2 = 0;
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                _sum += sudoku[i][k];
                _sum2 += sudoku[k][i];
            }
            if (_sum != sumOfNumbers) {
                System.out.println("NO");
                return;
            }
            if (_sum2 != sumOfNumbers) {
                System.out.println("NO");
                return;
            }
            _sum = 0;
            _sum2 = 0;
        }

        System.out.println("YES");
    }

    private static int sumOfNumbers(int N) {
        return N * (N + 1) / 2;
    }
}