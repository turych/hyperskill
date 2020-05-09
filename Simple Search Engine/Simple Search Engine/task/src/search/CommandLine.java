package search;

import java.util.TreeMap;

public class CommandLine {

    private static final TreeMap<String, String> options = new TreeMap<>();

    public CommandLine(String[] args) {
        parse(args);
    }

    public String get(String argName) {
        return options.get(argName);
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                if (args[i + 1].charAt(0) == '-') {
                    throw new IllegalArgumentException("Expected value after: " + args[i]);
                }
                options.put(args[i], args[++i]);
            }
        }
    }
}
