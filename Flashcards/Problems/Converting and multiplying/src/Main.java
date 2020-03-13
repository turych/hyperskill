import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int result;
        while (!input.equals("0")) {
            try {
                result = Integer.parseInt(input) * 10;
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Invalid user input: " + input);
            }

            input = scanner.nextLine();
        }
    }
}