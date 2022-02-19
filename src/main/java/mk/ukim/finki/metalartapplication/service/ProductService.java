package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Dimension;
import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.dto.DimensionDTORequest;
import mk.ukim.finki.metalartapplication.model.dto.search.PagedResponse;
import mk.ukim.finki.metalartapplication.model.dto.search.SearchRequest;
import mk.ukim.finki.metalartapplication.model.enumeration.Shape;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public PagedResponse<Product> getAll(SearchRequest request);
    public Product getProductById(Long id);
    public boolean deleteProduct(Long id);
    public boolean bulkDeleteProducts(List<Long> ids);
    public Product updateProduct(Long id, String name,
                                           String description,
                                           Double price,
                                           Long amount,
                                           boolean available,
                                           Shape shape,
                                 DimensionDTORequest dimension, MultipartFile image,
                                 Long dimensionId) throws IOException;
    public Product createProduct (String name, String description,
                                            Double price,
                                            Long amount,
                                            boolean available,
                                            Shape shape,
                                            DimensionDTORequest dimension,
                                            MultipartFile image) throws IOException;
    public List<Product> getSimilarProductsTo(Long id);
    public PagedResponse<Product> search(SearchRequest request, String searchString);
}
