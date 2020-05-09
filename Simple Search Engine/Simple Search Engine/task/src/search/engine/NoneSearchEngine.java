package search.engine;

import search.SearchIndex;

import java.util.*;

public class NoneSearchEngine extends SearchService{

    public NoneSearchEngine(SearchIndex searchIndex, List<String> searchData) {
        super(searchIndex, searchData);
    }

    @Override
    public String[] search(String search) {

        String[] searchWords = search.toLowerCase().split(" ");

        SortedSet<Integer> indexes = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < searchWords.length; i++) {
            if (getSearchIndex().contains(searchWords[i])) {
                List<Integer> foundIndex = getSearchIndex().getIndexes(searchWords[i]);
                indexes.addAll(foundIndex);
            }
        }

        List<String> result = new ArrayList<>(getSearchData());

        indexes.forEach(index -> {
            result.remove((int)index);
        });

        return result.toArray(new String[0]);
    }
}
