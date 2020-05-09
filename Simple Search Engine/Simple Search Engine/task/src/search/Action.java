package search;

public enum Action {
    FIND(1),
    PRINT_ALL(2),
    EXIT(0);

    private int id;

    Action(int action) {
        this.id = action;
    }

    public int getId() {
        return id;
    }


}
