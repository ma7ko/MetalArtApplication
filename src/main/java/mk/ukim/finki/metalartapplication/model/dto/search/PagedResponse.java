package mk.ukim.finki.metalartapplication.model.dto.search;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {

    private List<T> content;
    private Long count;
    private Long totalCount;

    public PagedResponse(List<T> content, Long count, Long totalCount) {
        this.content = content;
        this.count = count;
        this.totalCount = totalCount;
    }
}
