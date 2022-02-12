package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findAllByNameContaining(String string, Pageable pageable);
}
