package search;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataService {
    public List<String> load(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        Scanner scanner = new Scanner(fileReader);

        List<String> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine());
        }
        return data;
    }

    public void printAll(List<String> data) {
        data.forEach(System.out::println);
    }
}
