package machine.drink;

public class Cappuccino implements Coffee{
    int water = 200;
    int milk = 100;
    int beans = 12;
    int cost = 6;

    @Override
    public int water() {
        return water;
    }

    @Override
    public int milk() {
        return milk;
    }

    @Override
    public int beans() {
        return beans;
    }

    @Override
    public int cost() {
        return cost;
    }
}