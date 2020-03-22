import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Character> arr = new ArrayList<>();

            int character = reader.read();
            while (character != -1) {
                arr.add((char) character);
                character = reader.read();
            }

            for (int i = arr.size() - 1; i >= 0; i--) {
                System.out.print(arr.get(i));
            }
        }
    }
}