package mk.ukim.finki.metalartapplication.model.dto;

import mk.ukim.finki.metalartapplication.model.Product;
import mk.ukim.finki.metalartapplication.model.Role;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationResponse {

    private final String jwt;
    private final String username;
    private final Role role;
    private final List<Product> products;

    public AuthenticationResponse(String jwt, String username, Role role, List<Product> products) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
        this.products = products;
    }

    public String getJwt() {
        return jwt;
    }

    public Role getRole() { return role; }

    public String getUsername() { return username; }

    public List<Product> getProducts() { return this.products; }
}
