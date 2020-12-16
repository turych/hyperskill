package processor.matrix;

import java.util.Arrays;

public class Matrix {
    public final int rows;
    public final int cols;
    public final double[][] matrix;

    public Matrix(double[][] m) {
        this.matrix = m;
        rows = m.length;
        cols = m[0].length;
    }

    public boolean isSquare() {
        return rows == cols;
    }

    public boolean isDimensionEquals(Matrix b) {
        return rows == b.rows && cols == b.cols;
    }

    public boolean isMultiplied(Matrix b) {
        return cols == b.rows;
    }

    public double cell(int row, int col) {
        return matrix[row][col];
    }

    public void cell(int row, int col, double val) {
        matrix[row][col] = val;
    }

    public void add(Matrix b) {
        MatrixCalculator.add(this.matrix, b.matrix);
    }

    public void multipleByNumber(double m) {
        MatrixCalculator.multipleByNumber(matrix, m);
    }

    public Matrix multiply(Matrix b) {
        return new Matrix(MatrixCalculator.multiple(this.matrix, b.matrix));
    }

    public Matrix transpose(TranspositionType type) {
        return Transposition.transpose(this, type);
    }

    public double determinant() {
        return Utils.det(this.toArray());
    }

    public Matrix inverse() throws Exception {
        return new Matrix(Utils.inverse(this.toArray()));
    }

    public double[][] toArray() {
        return matrix;
    }

    public String toString() {
        final String regex = "\\[|\\]|,";
        StringBuilder str = new StringBuilder();
        for (int r = 0; r < matrix.length; r++) {
            str.append(Arrays.toString(matrix[r]).replaceAll(regex, ""))
                    .append("\n");
        }
        return str.toString();
    }
}
