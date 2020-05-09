package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchIndex {

    private HashMap<String, List<Integer>> index = new HashMap<>();

    public SearchIndex(List<String> data) {
        parseIndex(data);
    }

    public boolean contains(String query) {
        return index.containsKey(query);
    }

    public List<Integer> getIndexes(String query) {
        return index.get(query);
    }

    private void parseIndex(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            parseRow(i, data.get(i));
        }
    }
    
    private void parseRow(int index, String row) {
        String[] list = row.toLowerCase().split(" ");
        for (String str : list) {
            if (this.index.containsKey(str)) {
                List<Integer> indexes = this.index.get(str);
                indexes.add(index);
                this.index.replace(str, indexes);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(index);
                this.index.put(str, indexes);
            }
        }
    }
}
