package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.dto.ProductDTORequest;
import mk.ukim.finki.metalartapplication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = this.productService.getAll();
            return ResponseEntity.ok(products);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@RequestParam Long id) {
        try {
            Product product = this.productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@RequestParam Long id) {
        try {
            boolean deleted = this.productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestParam Long id,
                                                 @RequestBody ProductDTORequest productRequest) {
        try {
            Product product = this.productService.updateProduct(productRequest.getId(),
                    productRequest.getName(),
                    productRequest.getDescription(),
                    productRequest.getPrice(),
                    productRequest.getAmount(),
                    productRequest.isAvailable(),
                    productRequest.getShape(),
                    productRequest.getDimension());

            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTORequest productRequest) {
        try {
            Product product = this.productService.createProduct(productRequest.getName(),
                    productRequest.getDescription(),
                    productRequest.getPrice(),
                    productRequest.getAmount(),
                    productRequest.isAvailable(),
                    productRequest.getShape(),
                    productRequest.getDimension());

            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
