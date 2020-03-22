import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        String operator = scanner.next();
        long b = scanner.nextLong();

        Long result = null;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    System.out.println("Division by 0!");
                    break;
                }
                result = a / b;
                break;
            default:
                System.out.println("Unknown operator");
        }
        if (result != null) {
            System.out.println(result);
        }
    }
}