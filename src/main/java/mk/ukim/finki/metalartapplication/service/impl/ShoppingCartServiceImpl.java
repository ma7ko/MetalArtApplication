package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.ShoppingCart;
import mk.ukim.finki.metalartapplication.model.User;
import mk.ukim.finki.metalartapplication.repository.ProductRepository;
import mk.ukim.finki.metalartapplication.repository.ShoppingCartRepository;
import mk.ukim.finki.metalartapplication.repository.UserRepository;
import mk.ukim.finki.metalartapplication.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToUserCart(Long productId, String username) {
        if (productId == null || username == null || username.isEmpty())
            throw new InvalidParameterException();

        Optional<User> optionalUser = this.userRepository.findById(username);
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if (optionalProduct.isEmpty() || optionalUser.isEmpty())
            throw new InvalidParameterException();

        Product product = optionalProduct.get();
        ShoppingCart cart = optionalUser.get().getShoppingCart();

        List<Product> storedProducts = cart.getProducts();
        storedProducts.add(product);

        this.cartRepository.save(cart);
    }
}
