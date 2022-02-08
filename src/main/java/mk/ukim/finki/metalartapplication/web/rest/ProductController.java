package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.dto.DimensionDTORequest;
import mk.ukim.finki.metalartapplication.model.dto.ProductDTORequest;
import mk.ukim.finki.metalartapplication.model.dto.search.PagedResponse;
import mk.ukim.finki.metalartapplication.model.dto.search.SearchRequest;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;
import mk.ukim.finki.metalartapplication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<PagedResponse<Product>> getAllProducts(@RequestParam Integer page, @RequestParam Integer size) {
        try {
            PagedResponse<Product> products = this.productService.getAll(new SearchRequest(page, size));
            return ResponseEntity.ok(products);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}/similar-products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> similarProducts(@PathVariable Long id) {
        try {
            List<Product> products = this.productService.getSimilarProductsTo(id);
            return ResponseEntity.ok(products);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = this.productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        try {
            boolean deleted = this.productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody ProductDTORequest productRequest) {
        try {
            Product product = this.productService.updateProduct(id,
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
    public ResponseEntity<Product> createProduct(@RequestParam MultipartFile uploadedFile, @RequestParam Double width,
                                                 @RequestParam Double height, @RequestParam Double depth,
                                                 @RequestParam Long amount, @RequestParam boolean available,
                                                 @RequestParam String description, @RequestParam Double price,
                                                 @RequestParam Integer shape, @RequestParam String name) {
        try {
            Product product = this.productService.createProduct(name,
                    description,
                    price,
                    amount,
                    available,
                    Shape.values()[shape],
                    new DimensionDTORequest(width, height, depth),
                    uploadedFile);

            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/bulk-delete", method = RequestMethod.POST)
    public ResponseEntity bulkDeleteProducts(@RequestBody List<Long> ids) {
        try {
            boolean deleted = this.productService.bulkDeleteProducts(ids);
            return ResponseEntity.ok(deleted);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
