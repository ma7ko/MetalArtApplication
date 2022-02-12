package mk.ukim.finki.metalartapplication.model.dto;

import lombok.Data;

@Data
public class RegistrationResponse {
    private boolean success;

    public RegistrationResponse() {
    }

    public RegistrationResponse(boolean success) {
        this.success = success;
    }
}
