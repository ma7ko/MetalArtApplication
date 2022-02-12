package mk.ukim.finki.metalartapplication.model.dto;

import mk.ukim.finki.metalartapplication.model.Role;

public class AuthenticationResponse {

    private final String jwt;
    private final String username;
    private final Role role;

    public AuthenticationResponse(String jwt, String username, Role role) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public Role getRole() { return role; }

    public String getUsername() { return username; }
}
