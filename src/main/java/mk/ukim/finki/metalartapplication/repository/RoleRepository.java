package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    public Optional<Role> findByKey(String key);
}
