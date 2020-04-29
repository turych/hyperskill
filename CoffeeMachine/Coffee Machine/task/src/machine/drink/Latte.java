package machine.drink;

public class Latte implements Coffee{
    int water = 350;
    int milk = 75;
    int beans = 20;
    int cost = 7;

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