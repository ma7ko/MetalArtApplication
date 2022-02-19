package mk.ukim.finki.metalartapplication.model.dto.search.util;

import mk.ukim.finki.metalartapplication.model.dto.search.PageRequest;
import mk.ukim.finki.metalartapplication.model.dto.search.SearchRequest;

public final class SearchRequestUtilClass {
    private static final int DEFAULT_PAGE_SIZE = 12;
    private SearchRequestUtilClass() {}

    public static PageRequest toPageRequest(final SearchRequest request) {
        if (request == null) {
            return new PageRequest(0, DEFAULT_PAGE_SIZE);
        }

        final Integer requestedSize = request.getSize();
        return new PageRequest(request.getPage(), requestedSize == 0 ? DEFAULT_PAGE_SIZE : requestedSize);
    }
}
