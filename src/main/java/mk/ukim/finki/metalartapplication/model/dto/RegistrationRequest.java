package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String passwordConfirm;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String password, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
