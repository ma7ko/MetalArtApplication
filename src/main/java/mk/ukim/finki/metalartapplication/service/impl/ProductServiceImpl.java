package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Dimension;
import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.dto.DimensionDTORequest;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;
import mk.ukim.finki.metalartapplication.repository.DimensionRepository;
import mk.ukim.finki.metalartapplication.repository.ProductRepository;
import mk.ukim.finki.metalartapplication.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DimensionRepository dimensionRepository;

    public ProductServiceImpl(ProductRepository productRepository, DimensionRepository dimensionRepository) {
        this.productRepository = productRepository;
        this.dimensionRepository = dimensionRepository;
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
    public boolean bulkDeleteProducts(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            throw new InvalidParameterException();
        }
        this.productRepository.deleteAllById(ids);
        return true;
    }

    @Override
    public Product updateProduct(Long id, String name, String description, Double price, Long amount, boolean available, Shape shape, DimensionDTORequest dimension) {
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

        Optional<Dimension> dimCheck = this.dimensionRepository.findById(product.getDimension().getId());
        if (dimCheck.isEmpty()) {
            throw new InvalidParameterException();
        }
        Dimension dim = dimCheck.get();
        dim.setWidth(dimension.getWidth());
        dim.setHeight(dimension.getHeight());
        dim.setDepth(dimension.getDepth());
        product.setDimension(dim);
        return this.productRepository.save(product);
    }

    @Override
    public Product createProduct(String name, String description, Double price, Long amount, boolean available, Shape shape, DimensionDTORequest dimension, MultipartFile image) throws IOException {

        Dimension dimensionData = new Dimension(dimension.getWidth(), dimension.getHeight(), dimension.getDepth());
        this.dimensionRepository.save(dimensionData);
        Product product = new Product(name,
                description,
                price,
                amount,
                available,
                shape,
                dimensionData,
                image.getBytes());

        return this.productRepository.save(product);
    }

    @Override
    public List<Product> getSimilarProductsTo(Long id) {
        List<Product> products = this.productRepository.findAll();
        Random random = new Random();
        Integer num = random.nextInt(products.size() - 5);

        return products.stream()
                .filter(p -> !p.getId().equals(id))
                .skip(num)
                .limit(3)
                .collect(Collectors.toList());
    }
}
