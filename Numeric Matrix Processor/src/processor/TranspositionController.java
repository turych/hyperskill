package processor;

import processor.matrix.Matrix;
import processor.matrix.TranspositionType;
import processor.ui.TranspositionMenu;
import processor.ui.UI;
import processor.ui.UIException;

public class TranspositionController {

    private UI ui;
    private int userChoice;

    public TranspositionController(UI ui) {
        this.ui = ui;
    }

    public void run() {
        while (true) {
            ui.renderTranspositionMenu();
            userChoice = ui.requestUserChoice();
            TranspositionType type = null;
            switch (userChoice) {
                case TranspositionMenu.MAIN_DIAGONAL:
                    type = TranspositionType.MAIN_DIAGONAL;
                    break;
                case TranspositionMenu.SIDE_DIAGONAL:
                    type = TranspositionType.SIDE_DIAGONAL;
                    break;
                case TranspositionMenu.VERTICAL_LINE:
                    type = TranspositionType.VERTICAL_LINE;
                    break;
                case TranspositionMenu.HORIZONTAL_LINE:
                    type = TranspositionType.HORIZONTAL_LINE;
                    break;
                default:
                    System.out.println("Unknown command:");
            }

            if (type != null) {
                try {
                    Matrix a = new Matrix(ui.requestMatrix(UI.MATRIX_NONE));
                    Matrix b = a.transpose(type);
                    ui.printMatrix(b);
                } catch (UIException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
    }
}
