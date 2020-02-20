import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int counter = 0;

        if (n == 1) System.out.println(1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                counter++;
                System.out.print(i + " ");
                if (counter >= n) {
                    i = n;
                    break;
                }
            }
        }
    }
}