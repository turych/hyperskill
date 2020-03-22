import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int character = reader.read();
            StringBuilder stringBuilder = null;
            List<String> words = new ArrayList<>();

            while (character != -1) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                if (character != 32) {
                    stringBuilder.append(character);
                }
                if (character == 32 && stringBuilder.length() > 0) {
                    words.add(stringBuilder.toString());
                    stringBuilder = null;
                }
                character = reader.read();
            }
            if (stringBuilder != null && stringBuilder.length() > 0) {
                words.add(stringBuilder.toString());
            }
            System.out.println(words.size());
        }
    }
}