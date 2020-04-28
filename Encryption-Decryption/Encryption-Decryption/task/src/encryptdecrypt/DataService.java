package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataService {
    public void save(String fileName, String data) throws IOException {
        Files.writeString(Paths.get(fileName), data);
    }

    public String load(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void print(String data) {
        System.out.println(data);
    }
}
