package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import mk.ukim.finki.metalartapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register (@RequestBody RegistrationRequest registrationRequest) throws Exception {
        try {
            this.userService.register(registrationRequest.getUsername(),
                    registrationRequest.getPassword(),
                    registrationRequest.getPasswordConfirm());
        }

        catch (Exception exception) {
            throw new Exception(exception);
        }

        return ResponseEntity.ok("Success");
    }
}
