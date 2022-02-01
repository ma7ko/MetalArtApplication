package mk.ukim.finki.metalartapplication.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Role implements GrantedAuthority {

    @Id
    private String key;
    private String name;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return key;
    }
}
