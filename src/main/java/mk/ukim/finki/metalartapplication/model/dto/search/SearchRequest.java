package mk.ukim.finki.metalartapplication.model.dto.search;

import lombok.Data;

@Data
public class SearchRequest {
    private Integer size;
    private Integer page;

    public SearchRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}

