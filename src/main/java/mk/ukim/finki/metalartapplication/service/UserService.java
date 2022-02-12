package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User register (String username, String password, String passwordConfirm);
    public User verifyAccount(String username);
    public List<Product> getUserProducts(String username);
}
