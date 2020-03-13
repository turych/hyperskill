import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dictSize = Integer.parseInt(scanner.nextLine());

        List<String> dict = new ArrayList<>();
        for (int i = 0; i < dictSize; i++) {
            dict.add(scanner.nextLine().toLowerCase());
        }

        int lineQty = Integer.parseInt(scanner.nextLine());
        String[] words;
        HashSet<String> errors = new HashSet<>();

        for (int i = 0; i < lineQty; i++) {
            String text = scanner.nextLine();
            words = text.toLowerCase().split(" ");
            for (int j = 0; j < words.length; j++) {
                if (!dict.contains(words[j])) {
                    errors.add(words[j]);
                }
            }
        }

        errors.forEach(System.out::println);
    }
}