package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Long> {
}
