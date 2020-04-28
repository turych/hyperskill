// Posted from EduTools plugin
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        char sym = input.charAt(0);
        int counter = 1;
        for (int i = 1; i < input.length(); i++) {
            if (sym == input.charAt(i)) {
                counter++;
            } else {
                System.out.print(String.valueOf(sym) + counter);
                counter = 1;
                sym = input.charAt(i);
            }
        }
        System.out.print(String.valueOf(sym) + counter);
    }
}