package mk.ukim.finki.metalartapplication.service;

import mk.ukim.finki.metalartapplication.model.Mail;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;

import javax.mail.MessagingException;

public interface MailService {
    public void sendVerifyEmail(RegistrationRequest request) throws MessagingException;
}
