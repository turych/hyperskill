import java.util.Scanner;

public class Main {

    public static void print(String strArg) {
        System.out.println(String.format("print(\"%s\")", strArg));
    }

    private static void print(String str, int val) {
        System.out.println(String.format("print(\"%s\", %s)", str, val));
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int val = scanner.nextInt();
        print(str);
        print(str, val);
    }
}