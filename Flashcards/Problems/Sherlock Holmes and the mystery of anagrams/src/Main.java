import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String wordA = scanner.nextLine();
        String wordB = scanner.nextLine();

        System.out.println(isAnagram(wordA, wordB) ? "yes" : "no");
    }

    private static boolean isAnagram(String wordA, String wordB) {
        return parseWord(wordA.toLowerCase()).equals(parseWord(wordB.toLowerCase()));
    }

    private static HashMap<Character, Integer> parseWord(String word) {
        HashMap<Character, Integer> map = new HashMap<>();
        Integer value;
        for (char letter : word.toCharArray()) {
            value = map.getOrDefault(letter, 0);
            map.put(letter, ++value);
        }
        return map;
    }
}