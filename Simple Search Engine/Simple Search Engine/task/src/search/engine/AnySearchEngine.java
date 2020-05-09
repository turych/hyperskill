package search.engine;

import search.SearchIndex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AnySearchEngine extends SearchService{

    public AnySearchEngine(SearchIndex searchIndex, List<String> searchData) {
        super(searchIndex, searchData);
    }

    @Override
    public String[] search(String search) {

        String[] searchWords = search.toLowerCase().split(" ");

        HashSet<Integer> indexes = new HashSet<>();

        for (int i = 0; i < searchWords.length; i++) {
            if (getSearchIndex().contains(searchWords[i])) {
                List<Integer> foundIndex = getSearchIndex().getIndexes(searchWords[i]);
                indexes.addAll(foundIndex);
            }
        }

        List<String> result = new ArrayList<>();

        indexes.forEach(i -> result.add(getSearchData().get(i)));

        return result.toArray(new String[0]);
    }
}
