import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double R = scanner.nextDouble();
        double S = Math.PI * Math.pow(R, 2);
        System.out.println(S);
    }
}