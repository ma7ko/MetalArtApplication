package mk.ukim.finki.metalartapplication.web.controller;

import mk.ukim.finki.metalartapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user-mail")
public class UserMailController {
    private final UserService userService;

    public UserMailController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{username}/verify-account", method = RequestMethod.GET)
    public String verifyAccount(@PathVariable String username, HttpServletResponse response){
        try {
            this.userService.verifyAccount(username);
        } catch (Exception exception) {
            return "invalid-verification";
        }

        return "successful-verification";
    }
}
