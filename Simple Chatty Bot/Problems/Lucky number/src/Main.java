import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        int length = str.length();
        int halfLength = length / 2;
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < length; i++) {
            if (i < halfLength) {
                sum1 += (int) str.charAt(i);
            } else {
                sum2 += (int) str.charAt(i);
            }
        }

        System.out.println(sum1 == sum2 ? "YES" : "NO");
    }
}