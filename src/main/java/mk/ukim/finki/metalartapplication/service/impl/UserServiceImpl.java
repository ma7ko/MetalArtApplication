package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Role;
import mk.ukim.finki.metalartapplication.repository.RoleRepository;
import mk.ukim.finki.metalartapplication.repository.UserRepository;
import mk.ukim.finki.metalartapplication.service.UserService;
import mk.ukim.finki.metalartapplication.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User register(String username, String password, String passwordConfirm) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ) {
            throw new InvalidParameterException();
        }
        if (!password.equals(passwordConfirm)) {
            throw new InvalidParameterException();
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new EntityExistsException();
        }

        Role role = this.roleRepository.findByKey(User.DEFAULT_ROLE).orElseThrow(InvalidParameterException::new);
        User user = new User(username, password, role);

        return userRepository.save(user);
    }
}
