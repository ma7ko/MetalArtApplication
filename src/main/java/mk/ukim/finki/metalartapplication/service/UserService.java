package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User register (String username, String password, String passwordConfirm);
}
