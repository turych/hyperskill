package flashcards;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

    private static HashMap<String, String> cards = new HashMap<>();
    private static LinkedHashMap<String, Integer> errors = new LinkedHashMap<>();
    private static Scanner scanner;
    private static String input;
    private static List<String> log = new ArrayList<>();
    private static String exportFileNameOnExit;

    public static void main(String[] args) {

        initCommands(args);

        String actions = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";

        scanner = new Scanner(System.in);
        outPrint(actions);
        input = getInput();

        while (!input.equals("exit")) {
            switch (input) {
                case "add":
                    addCard();
                    break;
                case "remove":
                    removeCard();
                    break;
                case "import":
                    importData();
                    break;
                case "export":
                    exportData();
                    break;
                case "ask":
                    play();
                    break;
                case "log":
                    saveLog();
                    break;
                case "hardest card":
                    hardest();
                    break;
                case "reset stats":
                    resetStats();
                    break;
            }
            outPrint("\n" +actions);
            input = getInput();
        }

        scanner.close();
        outPrint("Bye bye!");
        if (exportFileNameOnExit != null) {
            saveDataToFile(exportFileNameOnExit);
        }
    }

    private static void initCommands(String[] args) {
        String importFileName;
        for (int i = 0; i < args.length;) {
            switch (args[i]) {
                case "-import":
                    importFileName = args[i + 1];
                    loadDataFromFile(importFileName);
                    i += 2;
                    break;
                case "-export":
                    exportFileNameOnExit = args[i + 1];
                    i += 2;
                    break;
                default:
                    i++;
                    break;
            }
        }
    }

    private static void hardest() {
        if (errors.size() == 0) {
            outPrint("There are no cards with errors.");
        } else {

            int maxErrors = 0;
            LinkedList<Entry<String, Integer>> list = new LinkedList<>();

            for(Entry<String, Integer> entry : errors.entrySet()) {
                if (maxErrors < entry.getValue()) {
                    list.clear();
                    list.add(entry);
                    maxErrors = entry.getValue();
                } else if (maxErrors == entry.getValue()) {
                    list.add(entry);
                }
            }

            if(list.size() == 1) {
                Entry<String, Integer> e = list.getFirst();
                outPrint("The hardest card is \"" + e.getKey() + "\". You have " + e.getValue() + " errors answering it.");
            } else {
                StringBuilder out = new StringBuilder("The hardest cards are");

                int counter = 0;
                for (Entry<String, Integer> entry: list) {
                    out.append(counter == 0 ? " \"" : ", \"").append(entry.getKey()).append("\"");
                    counter++;
                }

                out.append(". You have ").append(maxErrors).append(" errors answering them.");
                outPrint(out.toString());
            }
        }
    }

    private static void resetStats() {
        errors.clear();
        outPrint("Card statistics has been reset.");
    }

    private static void outPrint(String out) {
        log.add(out);
        System.out.println(out);
    }

    private static String getInput() {
        String input = scanner.nextLine();
        log.add(input);
        return input;
    }
    
    private static void saveLog() {
        outPrint("File name:");
        String fileName = getInput();
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            log.forEach(printWriter::println);
            outPrint("The log has been saved.");
        } catch (IOException e) {
            outPrint("An exception occurs " + e.getMessage());
        }
    }

    private static void play() {
        outPrint("How many times to ask?");
        int qtyAsks = Integer.parseInt(getInput());
        Object[] keys = cards.keySet().toArray(new String[0]);
        Random random = new Random();
        for (int i = 0; i < qtyAsks; i++) {
            String cardName = (String) keys[random.nextInt(cards.size())];
            String cardDefinition = cards.get(cardName);
            outPrint("Print the definition of \"" + cardName + "\":");

            input = getInput();

            if (cardDefinition.equals(input)) {
                outPrint("Correct answer.");
            } else {
                addWrongAnswer(cardName);
                String wrongCardName = getKeyByValue(input);
                if (cards.containsValue(input)) {
                    outPrint("Wrong answer. The correct one is \"" + cardDefinition + "\"" 
                            + ", you've just written the definition of \"" + wrongCardName + "\".");
                } else {
                    outPrint("Wrong answer. The correct one is \"" + cardDefinition + "\".");
                }
            }
        }
    }

    private static void addWrongAnswer(String cardName) {
        if (errors.containsKey(cardName)) {
            errors.replace(cardName, errors.get(cardName) + 1);
        } else {
            errors.put(cardName, 1);
        }
    }

    private static String getKeyByValue(String value) {
        for (Entry<String, String> entry : cards.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void exportData() {
        outPrint("File name:");
        String fileName = getInput();
        saveDataToFile(fileName);
    }

    private static void saveDataToFile(String fileName) {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            cards.forEach((key, value) -> {
                printWriter.println(key + ";" + value + ";" + errors.get(key));
            });
            outPrint(cards.size() + " cards have been saved.");
        } catch (IOException e) {
            outPrint("An exception occurs " + e.getMessage());
        }
    }

    private static void importData() {
        outPrint("File name:");
        String fileName = getInput();
        loadDataFromFile(fileName);
    }

    private static void loadDataFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            outPrint("File not found.");
            return;
        }

        Scanner reader = null;
        try(FileReader fileReader = new FileReader(file)) {
            reader = new Scanner(fileReader);

            int counter = 0;
            final int CARD_NAME_COL = 0;
            final int CARD_DEFN_COL = 1;
            final int CARD_ERRORS_COL = 2;

            while (reader.hasNextLine()) {
                String[] lineData = reader.nextLine().split(";");
                cards.put(lineData[CARD_NAME_COL], lineData[CARD_DEFN_COL]);
                errors.put(lineData[CARD_NAME_COL], Integer.parseInt(lineData[CARD_ERRORS_COL]));
                counter++;
            }
            outPrint(counter + " cards have been loaded.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(reader).close();
        }
    }

    private static void removeCard() {
        outPrint("The card:");
        input = getInput();
        if (!cards.containsKey(input)) {
            outPrint("Can't remove \"" + input + "\": there is no such card.");
            return;
        }
        cards.remove(input);
        errors.remove(input);
        outPrint("The card has been removed.");
    }

    private static void addCard() {
        String cardName;
        String cardDefinition;

        outPrint("The card:");
        cardName = getInput();
        if (cards.containsKey(cardName)) {
            outPrint("The card \"" + cardName + "\" already exists.");
            return;
        }

        outPrint("The definition of the card:");
        cardDefinition = getInput();
        if (cards.containsValue(cardDefinition)) {
            outPrint("The definition \"" + cardDefinition + "\" already exists.");
            return;
        }

        cards.put(cardName, cardDefinition);

        outPrint("The pair (\"" + cardName + "\":\"" + cardDefinition + "\") has been added.");
    }
}
