package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class BracketBalance {
    public static boolean check(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        Character bracket;
        boolean error = false;
        for (int i = 0; i < input.length(); i++) {
            bracket = input.charAt(i);
            if (bracket.equals('(') || bracket.equals('{') || bracket.equals('[')) {
                stack.push(bracket);
            } else {
                try {
                    int fromStack = stack.pop();
                    if (bracket.equals(')') && fromStack != bracket - 1) {
                        error = true;
                        break;
                    } else if ((bracket.equals(']') || bracket.equals('}')) && fromStack != bracket - 2) {
                        error = true;
                        break;
                    }
                } catch (NoSuchElementException e) {
                    error = true;
                    break;
                }
            }
        }
        return stack.isEmpty() && !error;
    }
}
