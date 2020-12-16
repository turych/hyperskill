package processor;

import processor.matrix.Matrix;
import processor.ui.MainMenu;
import processor.ui.UI;
import processor.ui.UIException;

public class MainController {

    private UI ui;
    private int userChoice;
    private Matrix a;
    private Matrix b;

    public MainController(UI ui) {
        this.ui = ui;
    }

    public void run() {
        while (true) {
            ui.renderMenu();
            userChoice = ui.requestUserChoice();
            switch (userChoice) {
                case MainMenu.ADD:
                    addAction();
                    break;
                case MainMenu.MULTIPLY_BY_CONST:
                    multipleConstAction();
                    break;
                case MainMenu.MULTIPLY:
                    multiplyAction();
                    break;
                case MainMenu.TRANSPOSE:
                    transposeAction();
                    break;
                case MainMenu.DETERMINANT:
                    determinantAction();
                    break;
                case MainMenu.INVERSE:
                    inverseAction();
                    break;
                case MainMenu.EXIT:
                    exitAction();
                    break;
                default:
                    System.out.println("Unknown command:");
            }
        }
    }

    private void addAction() {
        try {
            a = new Matrix(ui.requestMatrix(UI.MATRIX_N1));
            b = new Matrix(ui.requestMatrix(UI.MATRIX_N2));
            if (a.isDimensionEquals(b)) {
                a.add(b);
                ui.printMatrix(a);
            } else {
                ui.printError();
            }
        } catch (UIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void multipleConstAction() {
        try {
            a = new Matrix(ui.requestMatrix(UI.MATRIX_NONE));
            a.multipleByNumber(ui.requestDouble());
            ui.printMatrix(a);
        } catch (UIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void multiplyAction() {
        try {
            a = new Matrix(ui.requestMatrix(UI.MATRIX_N1));
            b = new Matrix(ui.requestMatrix(UI.MATRIX_N2));
            if (a.isMultiplied(b)) {
                Matrix c = a.multiply(b);
                ui.printMatrix(c);
            } else {
                ui.printError();
            }
        } catch (UIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void transposeAction() {
        TranspositionController tr = new TranspositionController(ui);
        tr.run();
    }

    private void determinantAction() {
        try {
            a = new Matrix(ui.requestMatrix(UI.MATRIX_NONE));
            if (a.isSquare()) {
                double d = a.determinant();
                System.out.println("The result is:");
                System.out.println(d);
            }
        } catch (UIException e) {
            e.printStackTrace();
        }
    }

    private void inverseAction() {
        try {
            a = new Matrix(ui.requestMatrix(UI.MATRIX_NONE));
            if (a.isSquare()) {
                Matrix d = a.inverse();
                System.out.println("The result is:");
                System.out.println(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exitAction() {
        System.exit(0);
    }
}
