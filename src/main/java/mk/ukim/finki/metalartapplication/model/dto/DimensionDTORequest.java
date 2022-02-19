package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;

@Data
public class DimensionDTORequest {
    private Double width;
    private Double height;
    private Double depth;

    public DimensionDTORequest(Double width, Double height, Double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
