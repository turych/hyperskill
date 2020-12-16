package processor.matrix;

public class Utils {

    public static double det(double[][] matrix) {

        if (matrix.length == 2) {
            return detFor2x2(matrix);
        }

        double result = 0;

        int[] zeros = findZeros(matrix);
        int rowIndex = 0;
        int colIndex = 0;

        if (zeros[0] >= zeros[1]) {
            rowIndex = zeros[0];
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[rowIndex][c] != 0) {
                    result += matrix[rowIndex][c] * cofactor(rowIndex, c, matrix);
                }
            }
        } else {
            colIndex = zeros[1];
            for (int r = 0; r < matrix.length; r++) {
                if (matrix[r][colIndex] != 0) {
                    result += matrix[r][colIndex] * cofactor(r, colIndex, matrix);
                }
            }
        }
        return result;
    }

    private static double detFor2x2(double[][] m) {
        return m[0][0] * m[1][1] - m[1][0] * m[0][1];
    }

    public static double cofactor(int rowIndex, int colIndex, double[][] matrix) {
        double[][] minor = minor(rowIndex, colIndex, matrix);
        return Math.pow(-1, rowIndex + colIndex) * det(minor);
    }

    public static double[][] minor(int rowIndex, int colIndex, double[][] matrix) {
        int size = matrix.length - 1;
        double[][] minor = new double[size][size];

        int minorRow = 0;
        int minorCol = 0;

        for (int r = 0; r < matrix.length; r++) {
            if (r == rowIndex) {
                continue;
            }
            for (int c = 0; c < matrix.length; c++) {
                if (c == colIndex) {
                    continue;
                }
                minor[minorRow][minorCol] = matrix[r][c];
                minorCol++;
            }
            minorCol = 0;
            minorRow++;
        }
        return minor;
    }

    private static int[] findZeros(double[][] matrix) {
        int[] rowZeroCounter = new int[matrix.length];
        int[] colZeroCounter = new int[matrix.length];

        int maxRowIndex = 0;
        int maxColIndex = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == 0) {
                    rowZeroCounter[r]++;
                    if (maxRowIndex != r && rowZeroCounter[r] > rowZeroCounter[maxRowIndex]) {
                        maxRowIndex = r;
                    }
                    colZeroCounter[c]++;
                    if (maxColIndex != c && colZeroCounter[c] > colZeroCounter[maxColIndex]) {
                        maxColIndex = c;
                    }
                }
            }
        }

        return new int[] {maxRowIndex, maxColIndex};
    }

    public static double[][] inverse(double[][] matrix) throws Exception {

        double determinant = det(matrix);

        if (determinant == 0) {
            throw new Exception("This matrix doesn't have an inverse.");
        }

        if (matrix.length == 2) {
            return inverseFor2x2(matrix);
        }

        double[][] cofactors = findAllCofactors(matrix);

        Matrix transpose = Transposition.transpose(new Matrix(cofactors), TranspositionType.MAIN_DIAGONAL);

        for (int r = 0; r < transpose.rows; r++) {
            for (int c = 0; c < transpose.cols; c++) {
                transpose.cell(r, c, transpose.cell(r, c) / determinant);
            }
        }

        return transpose.toArray();
    }

    private static double[][] inverseFor2x2(double[][] matrix) {
        double[][] inversed = new double[matrix.length][matrix.length];
        inversed[0][0] = matrix[1][1];
        inversed[1][1] = matrix[0][0];
        inversed[0][1] = matrix[0][1] * -1;
        inversed[1][0] = matrix[1][0] * -1;

        double determinant = detFor2x2(inversed);
        inversed[0][0] /= determinant;
        inversed[1][1] /= determinant;
        inversed[0][1] /= determinant;
        inversed[1][0] /= determinant;

        return inversed;
    }

    public static double[][] findAllCofactors(double[][] matrix) {
        double[][] cofactors = new double[matrix.length][matrix.length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                cofactors[r][c] = cofactor(r, c, matrix);
            }
        }
        return cofactors;
    }
}
