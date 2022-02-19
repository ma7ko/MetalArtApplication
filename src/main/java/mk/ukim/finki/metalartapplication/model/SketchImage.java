package mk.ukim.finki.metalartapplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SketchImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;

    public SketchImage() {
    }

    public SketchImage(byte[] image) {
        this.image = image;
    }

}
