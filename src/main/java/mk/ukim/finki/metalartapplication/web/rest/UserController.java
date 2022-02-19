package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.dto.ProductToCartRequest;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationResponse;
import mk.ukim.finki.metalartapplication.service.MailService;
import mk.ukim.finki.metalartapplication.service.ShoppingCartService;
import mk.ukim.finki.metalartapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final ShoppingCartService cartService;

    public UserController(UserService userService, MailService mailService, ShoppingCartService cartService) {
        this.userService = userService;
        this.mailService = mailService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register (@RequestBody RegistrationRequest registrationRequest) {
        try {
            this.userService.register(registrationRequest.getUsername(),
                    registrationRequest.getPassword(),
                    registrationRequest.getPasswordConfirm());

            this.mailService.sendVerifyEmail(registrationRequest);

        }

        catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }

        RegistrationResponse response = new RegistrationResponse(true);
        return ResponseEntity.ok(response);
    }

}
