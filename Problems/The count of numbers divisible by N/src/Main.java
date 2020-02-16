import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int x = scanner.nextInt();

        int r1 = (n1 - 1) / x;
        int r2 = n2 / x;

        System.out.println(r2 - r1);
    }
}