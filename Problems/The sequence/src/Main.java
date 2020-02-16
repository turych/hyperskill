import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int divisibleBy = 4;
        int max = 0;
        int current;

        while (scanner.hasNextInt()) {
            current = scanner.nextInt();
            if (current % divisibleBy == 0 && current > max) {
                max = current;
            }
        }

        System.out.println(max);
    }
}