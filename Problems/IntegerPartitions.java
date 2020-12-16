import java.util.Scanner;

/**
 * Number of decompositions (Integer Partitions)
 *
 * Each number can be broken down into parts, or addends.
 * For example, number 3 may be broken down into such addends as 1 + 1 + 1, 2 + 1, 3.
 * This procedure is known as decomposition.
 * In this task, you'll need to find out all decompositions of number N and list its positive addends.
 * The decomposition should be printed in lexicographical order.
 *
 * For example:
 * Input: 5
 * Output:
 * 1 1 1 1 1
 * 2 1 1 1
 * 2 2 1
 * 3 1 1
 * 3 2
 * 4 1
 * 5
 *
 * Each decomposition should consist of the addends in a descending order,
 * where each subsequent number of the list is equal or less than the previous one.
 */

public class IntegerPartitions {

    public static void printPartitions(int target) {
        printPartitions(target, target, "");
    }

    private static void printPartitions(int target, int max, String suffix) {
        if (target == 0) {
            System.out.println(suffix.trim());
        } else {
            for (int i = 1; i <= max && i <= target; i++) {
                printPartitions(target - i, i, suffix + " " + i);
            }
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        printPartitions(n);
    }
}