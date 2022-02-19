package mk.ukim.finki.metalartapplication.model;

import lombok.Data;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long amount;
    private boolean available;

    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private Shape shape;

    @OneToOne
    private Dimension dimension;

    public Product() {
    }

    public Product(String name, String description, Double price, Long amount, boolean available, Shape shape, Dimension dimension, byte[] image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.shape = shape;
        this.available = available;
        this.dimension = dimension;
        this.image = image;
    }
}
