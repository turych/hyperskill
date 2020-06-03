package calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static Scanner scanner;
    private static String input;

    private static Map<String, String> variables;

    private static PolishNotation polishNotation;

    private static List<String> postfix;

    public static void main(String[] args) {

        variables = new HashMap<>();
        scanner = new Scanner(System.in);
        polishNotation = new PolishNotation();

        while (true) {
            input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                Integer.parseInt(input);
                System.out.println(input);
                continue;
            } catch (NumberFormatException e) {
                // Ok. Input is not a single digit.
            }

            if (isCommand()) {
                doCommand();
                continue;
            }

            if (isVariable()) {
                try {
                    setVariable();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (!isExpressionValid()) {
                System.out.println("Invalid expression");
                continue;
            }

            calculate();
        }
    }

    private static boolean isCommand() {
        return input.matches("\\/[a-z]+");
    }

    private static boolean isVariable() {
        return input.matches(".*=.*");
    }

    private static boolean isExpressionValid() {
        return polishNotation.isInfixValid(input);
    }

    /**
     * @throws NumberFormatException If variable name is not [a-zA-Z] of if variable(as value) is not found.
     */
    private static void setVariable() throws NumberFormatException {
        String[] data = input.replaceAll("\\s+", "").split("=");

        if (data.length > 2) {
            throw new NumberFormatException("Invalid assignment");
        }

        Pattern patternABC = Pattern.compile("[a-zA-Z]*");

        String varName = data[0];
        String varValue;

        Matcher matcherVarName = patternABC.matcher(varName);
        if (!matcherVarName.matches()) {
            throw new NumberFormatException("Invalid identifier");
        }

        Matcher matcherVarValueABC = patternABC.matcher(data[1]);
        Pattern patternNumeric = Pattern.compile("\\d*");
        Matcher matcherVarValueNumeric = patternNumeric.matcher(data[1]);
        if (matcherVarValueABC.matches()) {
            if (variables.containsKey(data[1])) {
                varValue = variables.get(data[1]);
            } else {
                throw new NumberFormatException("Unknown variable");
            }
            variables.put(varName, varValue);
            return;
        }

        if (matcherVarValueNumeric.matches()) {
            variables.put(varName, data[1]);
            return;
        }

        throw new NumberFormatException("Invalid assignment");
    }

    private static void doCommand() {
        switch (input) {
            case "/exit":
                System.out.println("Bye!");
                System.exit(0);
            case "/help":
                System.out.println("The program calculates expression. Possible operators: +, -, *, /, ^");
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private static void calculate() {
        postfix = polishNotation.infixToPostfix(input);
        replaceVariables();
        calculatePostfix();
    }

    private static void calculatePostfix() {
        Deque<BigDecimal> stack = new ArrayDeque<>();
        BigDecimal digit;
        Operator operator;
        BigDecimal a;
        BigDecimal b;
        for (String pItem : postfix) {
            try {
                 digit = new BigDecimal(Double.valueOf(pItem));
                 stack.push(digit);
            } catch (NumberFormatException e) {
                 b = stack.pop();
                 a = stack.pop();
                 operator = Operator.find(pItem);
                 if (operator == null) {
                     System.out.println("Operator " + " is invalid.");
                     break;
                 }
                 switch (Objects.requireNonNull(operator)) {
                     case PLUS:
                         stack.push(a.add(b));
                         break;
                     case MINUS:
                         stack.push(a.subtract(b));
                         break;
                     case MULTIPLY:
                         stack.push(a.multiply(b));
                         break;
                     case DIVIDE:
                         stack.push(a.divide(b));
                         break;
                     case POW:
                         stack.push(a.pow(Integer.parseInt(b.toString())));
                         break;
                 }
            }
        }
        DecimalFormat df = new DecimalFormat("###.#");
        System.out.println(df.format(stack.pop()));
    }

    /**
     * Replace variables in postfix array.
     */
    private static void replaceVariables() {
        for (int i = 0; i < postfix.size(); i++) {
            if (variables.containsKey(postfix.get(i))) {
                postfix.set(i, variables.get(postfix.get(i)));
            }
        }
    }
}
