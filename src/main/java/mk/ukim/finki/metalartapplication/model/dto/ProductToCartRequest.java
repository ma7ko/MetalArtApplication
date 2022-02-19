package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;

@Data
public class ProductToCartRequest {

    private String username;
    private Long productId;

    public ProductToCartRequest() {
    }

    public ProductToCartRequest(String username, Long productId) {
        this.username = username;
        this.productId = productId;
    }
}
