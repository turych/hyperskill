package calculator;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POW("^");

    private final String operator;

    Operator(String s) {
        operator = s;
    }

    public String getOperator() {
        return operator;
    }

    public static Operator find(String f) {
        for (Operator value : values()) {
            if (f.equals(value.operator)) {
                return value;
            }
        }
        return null;
    }
}
