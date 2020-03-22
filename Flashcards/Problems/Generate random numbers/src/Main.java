import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Random random = new Random(a + b);
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += random.nextInt(b - a + 1) + a;
        }

        System.out.println(sum);
    }
}