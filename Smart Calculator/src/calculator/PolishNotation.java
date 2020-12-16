package calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolishNotation {

    /**
     * Parentheses states.
     */
    private final byte P_OPEN = 1;
    private final byte P_CLOSE = 0;

    /**
     * Operators.
     *
     * Pairs: operator => priority
     */
    private Map<Character, Integer> operators;

    /**
     * Parentheses.
     *
     * Pairs: bracket => state
     */
    private Map<Character, Byte> parentheses;

    public PolishNotation() {
        init();
    }

    public List<String> infixToPostfix(String infix) {
        infix = infixFilter(infix);

        if (!isInfixValid(infix)) {
            return null;
        }

        String splitRegex = "(\\d+|[a-zA-Z]+|[+\\-*\\\\/^()])";

        Pattern pattern = Pattern.compile(splitRegex);
        Matcher matcher = pattern.matcher(infix);

        List<String> infixArray = new ArrayList<>();
        matcher.results().forEach(r -> infixArray.add(r.group()));

        Deque<Character> stack = new ArrayDeque<>();
        List<String> postfix = new ArrayList<>();
        char stackItem;
        char operator;
        int operatorPriority;
        int stackItemPriority;

        for (String part : infixArray) {
            if (isOperator(part.charAt(0))) {
                operator = part.charAt(0);
                if (!stack.isEmpty() && !isParentheses(stack.peek())) {
                    stackItemPriority = operators.get(stack.peek());
                    operatorPriority = operators.get(operator);
                    if (operatorPriority <= stackItemPriority) {
                        while (!stack.isEmpty() && !isParentheses(stack.peek())) {
                            stackItem = stack.pop();
                            postfix.add(String.valueOf(stackItem));
                        }
                    }
                }
                stack.push(operator);
            } else if (isOperand(part)) {
                postfix.add(part);
            } else if (isParentheses(part.charAt(0))) {
                byte bracketState = parentheses.get(part.charAt(0));
                if (bracketState == P_OPEN) {
                    stack.push(part.charAt(0));
                } else {
                    while (!isParentheses(stack.peek())) {
                        stackItem = stack.pop();
                        postfix.add(String.valueOf(stackItem));
                    }
                    // remove closed bracket
                    stack.pop();
                }
            }
        }

        // remove all operators from stack adn add to postfix
        while (!stack.isEmpty()) {
            postfix.add(String.valueOf(stack.pop()));
        }

        return postfix;
    }

    private void init() {
        operators = new HashMap<>();
        operators.put('+', 0);
        operators.put('-', 0);
        operators.put('*', 1);
        operators.put('/', 1);
        operators.put('^', 2);

        // TODO: add other parentheses: {} []
        parentheses = new HashMap<>();
        parentheses.put('(', P_OPEN);
        parentheses.put(')', P_CLOSE);
    }

    /**
     * Remove extra operators and spaces
     *
     * @param infix String
     * @return String
     */
    private String infixFilter(String infix) {
        return infix
                // replace all pluses & all EVEN minuses to "+"
                .replaceAll("(\\+{2,}|(--)+)", "+")
                // replace all ODD minuses to "-"
                .replaceAll("(-(--)*|\\+-)", "-")
                // delete all spaces
                .replaceAll("\\s+", "");
    }

    public boolean isInfixValid(String infix) {
        infix = infixFilter(infix);
        boolean expressionValid = infix.matches("([(+-]?\\(*(\\d+|[a-zA-Z]+))([+\\-*\\\\/^]\\(*(\\d+|[a-zA-Z]+)\\)*)*");
        boolean bracketsBalanced = BracketBalance.check(infix.replaceAll("[^()\\[\\]{}]", ""));
        return expressionValid && bracketsBalanced;
    }

    private boolean isOperator(char c) {
        return operators.containsKey(c);
    }

    private boolean isOperand(String s) {
        return isNumber(s) || isVariable(s);
    }

    private boolean isParentheses(Character c) {
        return parentheses.containsKey(c);
    }

    private boolean isNumber(String s) {
        String regex = "[+-]?(0|[1-9]\\d*)([.,](\\d*[1-9]|0))?";
        return s.matches(regex);
    }

    private boolean isVariable(String s) {
        return s.matches("[a-zA-Z]+");
    }
}
