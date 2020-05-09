package search.engine;

import search.SearchIndex;

import java.util.List;

public abstract class SearchService implements SearchEngine {
    private SearchIndex searchIndex;
    private List<String> searchData;

    public SearchService(SearchIndex searchIndex, List<String> searchData) {
        this.searchIndex = searchIndex;
        this.searchData = searchData;
    }

    public SearchIndex getSearchIndex() {
        return searchIndex;
    }

    public List<String> getSearchData() {
        return searchData;
    }
}
