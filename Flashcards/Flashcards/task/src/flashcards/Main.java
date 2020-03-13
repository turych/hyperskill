package flashcards;

import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
//        Set<String> nameSet = new TreeSet<>(Arrays.asList("Mr.Green", "Mr.Yellow", "Mr.Red"));
//        nameSet.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");

        int numOfCards = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, String> cards = new LinkedHashMap<>();

        String cardName;
        String cardDef;
        for (int i = 0; i < numOfCards; i++) {
            System.out.println("The card #" + (i + 1) + ":");
            cardName = scanner.nextLine().toLowerCase();
            while (cards.containsValue(cardName)) {
                System.out.println("The card \"" + cardName + "\" already exists. Try again:");
                cardName = scanner.nextLine().toLowerCase();
            }

            System.out.println("The definition of the card #" + (i + 1) + ":");
            cardDef = scanner.nextLine().toLowerCase();
            while (cards.containsKey(cardDef)) {
                System.out.println("The definition \"" + cardDef + "\" already exists. Try again:");
                cardDef = scanner.nextLine().toLowerCase();
            }

            cards.put(cardDef, cardName);
        }

        String input;
        for (Entry entry : cards.entrySet()) {
            System.out.println("Print the definition of \"" + entry.getValue() + "\":");

            input = scanner.nextLine().toLowerCase();

            if (input.equals(entry.getKey())) {
                System.out.println("Correct answer.");
            } else {
                if (cards.containsKey(input)) {
                    System.out.print("Wrong answer. The correct one is \"" + entry.getKey() + "\"");
                    System.out.println(", you've just written the definition of \"" + cards.get(input) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + entry.getKey() + "\".");
                }
            }
        }
    }
}
