package search;

import search.engine.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static CommandLine commandLine;
    private static Scanner scanner;
    private static DataService dataService;
    private static List<String> searchData;
    private static SearchIndex searchIndex;

    private static ContactSearch contactSearch;

    public static void main(String[] args) {

        commandLine = new CommandLine(args);
        dataService = new DataService();
        scanner = new Scanner(System.in);

        try {
            searchData = dataService.load(commandLine.get("--data"));
            searchIndex = new SearchIndex(searchData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            displayMenu();
            int action = Integer.parseInt(scanner.nextLine());

            if (action == Action.FIND.getId()) {
                search();
            } else if (action == Action.PRINT_ALL.getId()) {
                dataService.printAll(searchData);
            } else if (action == Action.EXIT.getId()) {
                System.out.println("Bye!");
                System.exit(0);
            } else {
                System.out.println("unknown command");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    private static void search() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();

        System.out.println("Enter a name or email to search all suitable people.");
        String search = scanner.nextLine().toLowerCase().trim();

        contactSearch = new ContactSearch(engineFactory(strategy));

        String[] result = contactSearch.search(search);
        if (result.length > 0) {
            System.out.println("Found people:");
            for (String contact : result) {
                System.out.println(contact);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static SearchEngine engineFactory(String strategy) {
        SearchStrategy searchStrategy = SearchStrategy.valueOf(strategy);
        switch (searchStrategy) {
            case ALL:
                return new AllSearchEngine(searchIndex, searchData);
            case ANY:
                return new AnySearchEngine(searchIndex, searchData);
            case NONE:
                return new NoneSearchEngine(searchIndex, searchData);
            default:
                return null;
        }
    }
}
