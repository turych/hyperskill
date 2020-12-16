package processor.matrix;

public class MatrixCalculator {

    public static void add(double[][] a, double[][] b) {
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[r].length; c++) {
                a[r][c] += b[r][c];
            }
        }
    }

    public static void multipleByNumber(double [][] matrix, double m) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c] *= m;
            }
        }
    }

    public static double[][] multiple(double [][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int bCols = b.length;
        double[][] c = new double[rows][cols];
        for (int aRow = 0; aRow < rows; aRow++) {
            for (int bCol = 0; bCol < cols; bCol++) {
                for (int bRow = 0; bRow < bCols; bRow++) {
                    c[aRow][bCol] += a[aRow][bRow] * b[bRow][bCol];
                }
            }
        }
        return c;
    }
}
