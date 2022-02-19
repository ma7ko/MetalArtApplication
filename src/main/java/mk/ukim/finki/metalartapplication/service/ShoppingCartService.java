package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.ShoppingCart;
import mk.ukim.finki.metalartapplication.model.User;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart getCartByUser(User user);
    public List<Product> getUserProducts(User user);
    public void addProductToUserCart(Long productId, User user);
}
