import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{12,}";

        System.out.println(input.matches(regex) ? "YES" : "NO");
    }
}