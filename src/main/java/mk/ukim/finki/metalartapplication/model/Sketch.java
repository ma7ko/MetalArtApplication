package mk.ukim.finki.metalartapplication.model;


import lombok.Data;
import mk.ukim.finki.metalartapplication.model.enumeration.SketchStatus;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sketch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String information;

    @Enumerated(EnumType.STRING)
    private SketchStatus status;

    @OneToOne
    private User author;

    @ManyToOne
    private Product product;

    @ManyToMany
    private List<SketchImage> images;

    public Sketch() {
    }

    public Sketch(String name, String information, SketchStatus status, User author, Product product) {
        this.name = name;
        this.information = information;
        this.status = status;
        this.author = author;
        this.product = product;
        this.images = new ArrayList<>();
    }
}
