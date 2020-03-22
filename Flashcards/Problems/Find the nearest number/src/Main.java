import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        int[] numbers = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();

        int N = scanner.nextInt();
        int minDistance = 100;
        int currentDistance;
        List<Integer> result = new ArrayList<>();


        for (int x : numbers) {
            currentDistance = Math.abs(N - x);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                result.clear();
                result.add(x);
            } else if (currentDistance == minDistance) {
                result.add(x);
            }
        }

        Collections.sort(result);
        for (int number : result) {
            System.out.print(number + " ");
        }
    }
}