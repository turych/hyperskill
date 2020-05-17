import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "((2[0-5][0-5]|1\\d\\d|\\d\\d?)\\.){3}(2[0-5][0-5]|1\\d\\d|\\d\\d?)";

        System.out.println(input.matches(regex) ? "YES" : "NO");
    }
}