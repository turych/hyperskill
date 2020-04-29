package machine.drink;

public class Espresso implements Coffee{
    int water = 250;
    int milk = 0;
    int beans = 16;
    int cost = 4;

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
