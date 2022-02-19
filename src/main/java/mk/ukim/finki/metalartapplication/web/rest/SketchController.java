package mk.ukim.finki.metalartapplication.web.rest;

import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import mk.ukim.finki.metalartapplication.model.dto.SketchRequest;
import mk.ukim.finki.metalartapplication.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sketch")
public class SketchController {

    private MailService mailService;

    public SketchController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> sendSketch(@RequestParam MultipartFile uploadedFile, @RequestParam Double width,
                                        @RequestParam Double height, @RequestParam Double depth,
                                        @RequestParam String description, @RequestParam String name, @RequestParam String metric) throws MessagingException, IOException {
        this.mailService.sendSketchEmail(uploadedFile, name, description, width, height, depth, metric);
        return ResponseEntity.ok(null);
    }
}
