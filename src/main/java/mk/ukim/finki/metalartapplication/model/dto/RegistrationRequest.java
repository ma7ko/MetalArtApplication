package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String email, String password, String passwordConfirm) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
