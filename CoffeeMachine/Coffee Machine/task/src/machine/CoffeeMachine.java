package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static Scanner scanner;

    private static Tank tank;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        tank = new Tank(400, 540, 120, 9, 550);
        MachineActions actions = new MachineActions(scanner, tank);
        actions.run();
    }
}
