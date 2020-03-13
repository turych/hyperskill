import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int posOfN = 0;
        int posOfM = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) posOfN = i;
            if (arr[i] == m) posOfM = i;
        }

        System.out.println(Math.abs(posOfN - posOfM) == 1);
    }
}