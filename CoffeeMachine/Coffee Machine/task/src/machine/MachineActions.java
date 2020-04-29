package machine;

import machine.drink.Coffee;

import java.util.Scanner;

public class MachineActions {
    private Scanner scanner;
    private Tank tank;

    public MachineActions(Scanner scanner, Tank tank) {
        this.scanner = scanner;
        this.tank = tank;
    }

    public void run() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String _action = scanner.nextLine();

            if (_action.isEmpty()) continue;

            Action action = Action.valueOf(_action.toUpperCase());

            switch (action) {
                case BUY:
                    buy();
                    break;
                case FILL:
                    fill();
                    break;
                case TAKE:
                    takeMoney();
                    break;
                case REMAINING:
                    displayStorage();
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    public void buy() {
        while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            if (scanner.hasNextInt()) {
                int type = scanner.nextInt();
                Coffee coffee = CoffeeFactory.newInstance(type);
                if (tank.haveCup(coffee)) {
                    tank.prepare(coffee);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough water!");
                }
                return;
            } else {
                String command = scanner.nextLine();
                if (command.equals("back")) {
                    return;
                } else {
                    System.out.println("Unknown command");
                }
            }
        }
    }

    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        tank.addWater(scanner.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        tank.addMilk(scanner.nextInt());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        tank.addBeans(scanner.nextInt());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        tank.addDisposableCups(scanner.nextInt());
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d", tank.takeMoney());
    }

    public void displayStorage() {
        System.out.printf("The coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "%d of money\n",
                tank.getWater(), tank.getMilk(), tank.getBeans(), tank.getDisposableCups(), tank.getMoney());
    }
}
