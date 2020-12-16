package processor.matrix;

public class Transposition {
    public static Matrix transpose(Matrix a, TranspositionType type) {
        switch (type) {
            case MAIN_DIAGONAL:
                return main(a);
            case SIDE_DIAGONAL:
                return side(a);
            case VERTICAL_LINE:
                return vertical(a);
            case HORIZONTAL_LINE:
                return horizontal(a);
            default:
                return a;
        }
    }

    private static Matrix main(Matrix a) {
        double[][] b = new double[a.cols][a.rows];
        for (int row = 0; row < a.rows; row++) {
            for (int col = 0; col < a.cols; col++) {
                b[col][row] = a.matrix[row][col];
            }
        }
        return new Matrix(b);
    }

    private static Matrix side(Matrix a) {
        double[][] b = new double[a.cols][a.rows];
        for (int row = 0; row < a.rows; row++) {
            for (int col = 0; col < a.cols; col++) {
                b[a.rows - col - 1][a.cols - row - 1] = a.matrix[row][col];
            }
        }
        return new Matrix(b);
    }

    private static Matrix vertical(Matrix a) {
        double[][] b = new double[a.rows][a.cols];
        for (int row = 0; row < a.rows; row++) {
            for (int col = 0; col < a.cols; col++) {
                b[row][a.cols - col - 1] = a.matrix[row][col];
            }
        }
        return new Matrix(b);
    }

    private static Matrix horizontal(Matrix a) {
        double[][] b = new double[a.rows][a.cols];
        for (int row = 0; row < a.rows; row++) {
            for (int col = 0; col < a.cols; col++) {
                b[a.rows - row - 1][col] = a.matrix[row][col];
            }
        }
        return new Matrix(b);
    }
}
