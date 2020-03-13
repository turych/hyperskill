import java.util.Scanner;

import static java.lang.System.exit;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        if (size == 1) {
            System.out.println(scanner.nextInt());
            exit(0);
        }

        int max1 = 0, max2 = 0;
        int n;
        while(scanner.hasNextInt()) {
            n = scanner.nextInt();
            if (n > max2) {
                max2 = max1;
                max1 = n;
            }
        }

        System.out.println(max1 * max2);
    }
}