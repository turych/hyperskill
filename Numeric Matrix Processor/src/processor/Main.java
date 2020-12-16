package processor;

import processor.ui.UI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        MainController controller = new MainController(ui);
        controller.run();
    }
}
