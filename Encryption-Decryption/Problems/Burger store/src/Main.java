class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        BurgerStore burgerStore = new BurgerStore();
        Burger chinese = burgerStore.orderBurger("CH");
        Burger american = burgerStore.orderBurger("AM");
        Burger russian = burgerStore.orderBurger("RU");
    }
}

abstract class BurgerFactory {

    abstract Burger createBurger(String type);

    public Burger orderBurger(String type) throws InterruptedException {
        Burger burger = createBurger(type);
        if (burger == null) {
            System.out.println("Sorry, we are not able to create this kind of burger\n");
            return null;
        }
        System.out.println("Making a " + burger.getName());
        burger.putBun();
        burger.putCutlet();
        burger.putSauce();
        Thread.sleep(1500L);
        System.out.println("Done a " + burger.getName() + "\n");
        return burger;
    }
}

class BurgerStore extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        switch (type) {
            case "CH":
                return new ChineseBurger("Chinese Burger");
            case "AM":
                return new ChineseBurger("American Burger");
            case "RU":
                return new ChineseBurger("Russian Burger");
            default:
                return null;
        }
    }
}

abstract class Burger {
    private String name;

    Burger(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void putBun() {
        System.out.println("Putting bun");
    }

    void putCutlet() {
        System.out.println("Putting cutlet");
    }

    void putSauce() {
        System.out.println("Putting sauce");
    }

}

class ChineseBurger extends Burger {
    ChineseBurger(String name) {
        super(name);
    }
}

class AmericanBurger extends Burger {
    AmericanBurger(String name) {
        super(name);
    }
}

class RussianBurger extends Burger {
    RussianBurger(String name) {
        super(name);
    }
}