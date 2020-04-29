import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] matrix = new int[n][m];

        int max = Integer.MIN_VALUE;
        int maxRowIndex = 0;
        int maxColIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxRowIndex = i;
                    maxColIndex = j;
                }
            }
        }
        System.out.println(maxRowIndex + " " + maxColIndex);
    }
}