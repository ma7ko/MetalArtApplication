package mk.ukim.finki.metalartapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }
}
