package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
