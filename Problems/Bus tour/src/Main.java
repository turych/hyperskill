import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int busHeight = scanner.nextInt();
        int bridgeQty = scanner.nextInt();

        boolean crash = false;
        int bridgeHeight;
        int i = 1;
        while (scanner.hasNextInt()) {
            bridgeHeight = scanner.nextInt();
            if (busHeight >= bridgeHeight) {
                crash = true;
                System.out.println("Will crash on bridge " + i);
                break;
            }
            i++;
        }
        if (!crash) {
            System.out.println("Will not crash");
        }
    }
}