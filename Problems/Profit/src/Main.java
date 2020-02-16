import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double money = scanner.nextInt();
        double percent = (double) scanner.nextInt() / 100;
        double goal = scanner.nextInt();

        int year = 0;
        while (money < goal) {
            money += money * percent;
            year++;
        }

        System.out.println(year);
    }
}