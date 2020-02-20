import java.util.Scanner;

class Main {

    public static int divisibleBy = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        int counter = 0;
        int sum = 0;
        for (int i = x; i <= y; i++) {
            if (i % divisibleBy == 0) {
                sum += i;
                counter++;
            }
        }
        if (counter != 0) {
            float result = (float) sum / counter;
            System.out.println(result);
        } else {
            System.out.println(0);
        }
    }
}