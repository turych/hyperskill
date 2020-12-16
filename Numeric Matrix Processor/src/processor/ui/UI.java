package processor.ui;

import processor.matrix.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class UI {

    public static final String MATRIX_NONE = "";
    public static final String MATRIX_N1 = "first ";
    public static final String MATRIX_N2 = "second ";

    private static final String ERROR_MESSAGE = "The operation cannot be performed.";

    private final Scanner scanner;

    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void renderMenu() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit");
    }

    public void renderTranspositionMenu() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
    }

    public int requestUserChoice()
    {
        System.out.print("Your choice: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public double[][] requestMatrix(String matrixNumber) throws UIException {
        System.out.print("Enter size of " + matrixNumber + "matrix: ");
        String[] dimensions = scanner.nextLine().trim().split("\\s");
        int row = Integer.parseInt(dimensions[0]);
        int col = Integer.parseInt(dimensions[1]);

        double[][] matrix = new double[row][col];

        String[] inputLine;
        System.out.println("Enter " + matrixNumber + "matrix:");
        for (int i = 0; i < row; i++) {
            inputLine = scanner.nextLine().trim().split("\\s");
            if (inputLine.length != col) {
                throw new UIException(ERROR_MESSAGE);
            }
            matrix[i] = Arrays.stream(inputLine).mapToDouble(Double::parseDouble).toArray();
        }
        return matrix;
    }

    public double requestDouble() {
        System.out.print("Enter constant: ");
        return Double.parseDouble(scanner.nextLine().trim());
    }

    public void printMatrix(Matrix matrix) {
        System.out.println("The result is:");
        System.out.println(matrix.toString());
    }

    public void printError() {
        System.out.println(ERROR_MESSAGE);
    }
}
