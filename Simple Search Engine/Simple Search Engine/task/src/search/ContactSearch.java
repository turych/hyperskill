package search;

import search.engine.SearchEngine;

public class ContactSearch {
    private SearchEngine searchEngine;

    public ContactSearch(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String[] search(String search) {
        return searchEngine.search(search);
    }
}
