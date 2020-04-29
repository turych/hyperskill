package machine;

import machine.drink.Coffee;

public class Tank {
    private int water;
    private int milk;
    private int beans;
    private int disposableCups;
    private int money;

    public Tank(int water, int milk, int beans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.disposableCups = disposableCups;
        this.money = money;
    }

    public void addWater(int water) {
        this.water += water;
    }

    public void addMilk(int milk) {
        this.milk += milk;
    }

    public void addBeans(int beans) {
        this.beans += beans;
    }

    public void addDisposableCups(int disposableCups) {
        this.disposableCups += disposableCups;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public int getMoney() {
        return money;
    }

    public int takeMoney() {
        int _money = this.money;
        this.money = 0;
        return _money;
    }

    public boolean haveCup(Coffee coffee) {
        return disposableCups != 0
                && water - coffee.water() >= 0
                && milk - coffee.milk() >= 0
                && beans - coffee.beans() >= 0;
    }

    public void prepare(Coffee coffee) {
        reduceTank(coffee);
        addMoney(coffee.cost());
    }

    private void reduceTank(Coffee coffee) {
        water -= coffee.water();
        milk -= coffee.milk();
        beans -= coffee.beans();
        disposableCups -= 1;
    }
}