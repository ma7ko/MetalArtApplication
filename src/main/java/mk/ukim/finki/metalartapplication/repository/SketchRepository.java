package mk.ukim.finki.metalartapplication.repository;

import mk.ukim.finki.metalartapplication.model.Sketch;
import mk.ukim.finki.metalartapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SketchRepository extends JpaRepository<Sketch, Long> {

    public List<Sketch> findAllByAuthor(User author);
}
