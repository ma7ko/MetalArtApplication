package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Dimension;
import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
    public Product getProductById(Long id);
    public boolean deleteProduct(Long id);
    public Product updateProduct(Long id, String name,
                                           String description,
                                           Double price,
                                           Long amount,
                                           boolean available,
                                           Shape shape,
                                           Dimension dimension);
    public Product createProduct (String name, String description,
                                            Double price,
                                            Long amount,
                                            boolean available,
                                            Shape shape,
                                            Dimension dimension);
}
