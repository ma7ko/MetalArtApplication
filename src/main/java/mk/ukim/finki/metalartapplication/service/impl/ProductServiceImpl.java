package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Dimension;
import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;
import mk.ukim.finki.metalartapplication.repository.ProductRepository;
import mk.ukim.finki.metalartapplication.service.ProductService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        if (id == null) {
            throw new InvalidParameterException();
        }
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new InvalidParameterException();
        }

        return product.get();
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (id == null) {
            throw new InvalidParameterException();
        }
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product updateProduct(Long id, String name, String description, Double price, Long amount, boolean available, Shape shape, Dimension dimension) {
        Optional<Product> productCheck = this.productRepository.findById(id);

        if ( productCheck.isEmpty() ) {
            throw new InvalidParameterException();
        }

        Product product = productCheck.get();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setAmount(amount);
        product.setAvailable(available);
        product.setShape(shape);
        product.setDimension(dimension);
        return this.productRepository.save(product);
    }

    @Override
    public Product createProduct(String name, String description, Double price, Long amount, boolean available, Shape shape, Dimension dimension) {

        Product product = new Product(name,
                description,
                price,
                amount,
                available,
                shape,
                dimension);

        return this.productRepository.save(product);
    }
}
