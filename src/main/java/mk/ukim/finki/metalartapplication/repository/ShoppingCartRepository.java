package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.ShoppingCart;
import mk.ukim.finki.metalartapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    public Optional<ShoppingCart> findByUser(User user);
}
