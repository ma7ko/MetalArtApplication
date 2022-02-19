package mk.ukim.finki.metalartapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Dimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double width;
    private Double height;
    private Double depth;

    public Dimension() {
    }

    public Dimension(Double width, Double height, Double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
