package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Mail;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {
    public void sendVerifyEmail(RegistrationRequest request) throws MessagingException;
    public void sendSketchEmail(MultipartFile uploadedFile, String name, String description, Double width, Double height, Double depth, String metric) throws MessagingException, IOException;
}
