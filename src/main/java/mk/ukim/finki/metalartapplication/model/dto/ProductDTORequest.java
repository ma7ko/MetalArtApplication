package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;
import mk.ukim.finki.metalartapplication.model.Dimension;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
public class ProductDTORequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long amount;
    private boolean available;
    private Shape shape;
    private Dimension dimension;

    public ProductDTORequest() {
    }

    public ProductDTORequest(Long id, String name, String description, Double price, Long amount, boolean available, Shape shape, Dimension dimension) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.available = available;
        this.shape = shape;
        this.dimension = dimension;
    }
}
