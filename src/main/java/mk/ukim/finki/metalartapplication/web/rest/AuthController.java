package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.config.CustomUsernamePasswordAuthenticationProvider;
import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.Role;
import mk.ukim.finki.metalartapplication.model.User;
import mk.ukim.finki.metalartapplication.model.dto.AuthenticationRequest;
import mk.ukim.finki.metalartapplication.model.dto.AuthenticationResponse;
import mk.ukim.finki.metalartapplication.service.ProductService;
import mk.ukim.finki.metalartapplication.service.ShoppingCartService;
import mk.ukim.finki.metalartapplication.service.UserService;
import mk.ukim.finki.metalartapplication.web.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final UserService userService;
    private final JwtUtil jwtTokenUtil;
    private final ShoppingCartService cartService;

    public AuthController(CustomUsernamePasswordAuthenticationProvider authenticationProvider, UserService userService, JwtUtil jwtTokenUtil, ShoppingCartService cartService) {
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.cartService = cartService;
    }

    @RequestMapping("/login1")
    public String login() {
        return "ok";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            this.authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

        }
        catch (BadCredentialsException exception) {
            return ResponseEntity.badRequest().body("Bad Credentials");
        }

        final UserDetails userDetails = this.userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwtTokenUtil.generateToken(userDetails);

        List<Product> products = this.cartService.getUserProducts((User) userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getUsername(), (Role) userDetails.getAuthorities().stream().findFirst().get(), products));
    }
}
