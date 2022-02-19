package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.User;
import mk.ukim.finki.metalartapplication.model.dto.ProductToCartRequest;
import mk.ukim.finki.metalartapplication.service.ProductService;
import mk.ukim.finki.metalartapplication.service.ShoppingCartService;
import mk.ukim.finki.metalartapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @RequestMapping(value = "/{username}/get-products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getCartProducts(@PathVariable String username) {
        try {
            User user = (User) this.userService.loadUserByUsername(username);
            List<Product> products = this.cartService.getUserProducts(user);

            return ResponseEntity.ok(products);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCartRequest request) {
        try {
            User user = (User) this.userService.loadUserByUsername(request.getUsername());
            this.cartService.addProductToUserCart(request.getProductId(), user);

            return ResponseEntity.ok(null);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
