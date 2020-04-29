package machine;

import machine.drink.Cappuccino;
import machine.drink.Coffee;
import machine.drink.Espresso;
import machine.drink.Latte;

public class CoffeeFactory {

    private static final int ESPRESSO = 1;
    private static final int LATTE = 2;
    private static final int CAPPUCCINO = 3;

    public static Coffee newInstance(int type) {
        switch (type) {
            case ESPRESSO:
                return new Espresso();
            case LATTE:
                return new Latte();
            case CAPPUCCINO:
                return new Cappuccino();
            default:
                return null;
        }
    }
}
