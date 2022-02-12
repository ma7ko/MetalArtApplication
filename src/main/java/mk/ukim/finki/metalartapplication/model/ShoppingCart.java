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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public ShoppingCart() {
    }

    public ShoppingCart(String name) {
        this.products = new ArrayList<>();
    }
}
