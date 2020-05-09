package search.engine;

import search.SearchIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllSearchEngine extends SearchService {

    public AllSearchEngine(SearchIndex searchIndex, List<String> searchData) {
        super(searchIndex, searchData);
    }

    @Override
    public String[] search(String search) {
        String[] searchWords = search.toLowerCase().split(" ");

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < searchWords.length; i++) {
            if (getSearchIndex().contains(searchWords[i])) {
                List<Integer> foundIndex = getSearchIndex().getIndexes(searchWords[i]);
                indexes.addAll(foundIndex);
            }
        }

        HashMap<Integer, Integer> counts = new HashMap<>();
        indexes.forEach(index -> {
            if (counts.containsKey(index)) {
                counts.replace(index, counts.get(index) + 1);
            } else {
                counts.put(index, 1);
            }
        });

        List<String> result = new ArrayList<>();
        counts.forEach((index, count) -> {
            if (count == searchWords.length) {
                result.add(getSearchData().get(index));
            }
        });

        return result.toArray(new String[0]);
    }
}
