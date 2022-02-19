package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SketchRequest {
    private String name;
    private String description;
    private String metrics;
    private Double width;
    private Double height;
    private Double depth;
    private MultipartFile image;

    public SketchRequest() {
    }

    public SketchRequest(String name, String description, String metrics, Double width, Double height, Double depth, MultipartFile image) {
        this.name = name;
        this.description = description;
        this.metrics = metrics;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.image = image;
    }
}
