package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.ShoppingCart;
import mk.ukim.finki.metalartapplication.model.User;
import mk.ukim.finki.metalartapplication.repository.ProductRepository;
import mk.ukim.finki.metalartapplication.repository.ShoppingCartRepository;
import mk.ukim.finki.metalartapplication.repository.UserRepository;
import mk.ukim.finki.metalartapplication.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public ShoppingCart getCartByUser(User user) {
        if (user == null)
            throw new InvalidParameterException();

        Optional<ShoppingCart> optionalCart = this.cartRepository.findByUser(user);

        if (optionalCart.isEmpty())
            throw new InvalidParameterException();

        return optionalCart.get();
    }

    @Transactional
    @Override
    public List<Product> getUserProducts(User user) {
        if (user == null)
            throw new InvalidParameterException();

        ShoppingCart shoppingCart = this.getCartByUser(user);

        return shoppingCart.getProducts();
    }

    @Transactional
    @Override
    public void addProductToUserCart(Long productId, User user) {
        if (productId == null || user == null)
            throw new InvalidParameterException();

        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new InvalidParameterException();

        Product product = optionalProduct.get();
        ShoppingCart cart = this.getCartByUser(user);

        List<Product> storedProducts = cart.getProducts();

        if (storedProducts.contains(product)) {
            storedProducts.remove(product);
        } else {
            storedProducts.add(product);
        }

        cart.setProducts(storedProducts);

        this.cartRepository.save(cart);
    }
}
