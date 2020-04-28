package encryptdecrypt;

import java.io.IOException;
import java.util.TreeMap;

public class Main {

    private static final String ARG_MODE = "-mode";
    private static final String ARG_ALG = "-alg";
    private static final String ARG_KEY = "-key";
    private static final String ARG_DATA = "-data";
    private static final String ARG_IN = "-in";
    private static final String ARG_OUT = "-out";
    private static final String default_mode = "enc";
    private static final String default_key = "0";
    private static final String default_data = "";
    private static final String default_alg = "shift";
    private static final TreeMap<String, String> options = new TreeMap<>();

    private static DataService dataService;

    public static void main(String[] args) {

        dataService = new DataService();
        initOptions(args);

        StringShift stringShift = ShiftFactory.instance(options.get(ARG_ALG));
        ShiftService shiftService = new ShiftService(stringShift);

        String result = shiftService.shift(
                options.get(ARG_DATA),
                Integer.parseInt(options.get(ARG_KEY))
        );

        if (options.containsKey(ARG_OUT)) {
            try {
                dataService.save(options.get(ARG_OUT), result);
            } catch (IOException e) {
                stopWithError("Error. " + e.getMessage());
            }
        } else {
            dataService.print(result);
        }
    }

    private static void initOptions(String[] args) {
        options.put(ARG_MODE, default_mode);
        options.put(ARG_KEY, default_key);
        options.put(ARG_DATA, default_data);
        options.put(ARG_ALG, default_alg);

        parseCommandLine(args);

        if (options.get(ARG_MODE).toLowerCase().equals("dec")) {
            options.put(ARG_KEY, "-" + options.get(ARG_KEY));
        }

        if (options.containsKey(ARG_IN) && options.get(ARG_DATA).isEmpty()) {
            try {
                String loadedData = dataService.load(options.get(ARG_IN));
                options.put(ARG_DATA, loadedData);
            } catch (Exception e) {
                stopWithError("Error. " + e.getMessage());
            }
        }
    }

    private static void parseCommandLine(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                if (args[i + 1].charAt(0) == '-') {
                    stopWithError("Error. Expected value after: " + args[i]);
                }
                options.put(args[i], args[++i]);
            }
        }
    }

    private static void stopWithError(String message) {
        System.out.println(message);
        System.exit(0);
    }

    private static String coder(String message, int shift, String action) {

        if (message.isEmpty()) return null;

        char[] converted = new char[message.length()];


        int firstChar = 32; // " " 32
        int lastChar = 127; // "~" 126 + 1

        for (int i = 0; i < message.length(); i++) {
            int iChar = message.charAt(i);

            int shiftedChar = 0;
            switch (action) {
                case "enc":
                    shiftedChar = iChar + shift;
                    break;
                case "dec":
                    shiftedChar = iChar - shift;
                    break;
            }

            int encChar = shiftedChar % lastChar;
            if (encChar < firstChar) encChar += firstChar;

            converted[i] = (char) encChar;
        }
        return new String(converted);
    }
}
