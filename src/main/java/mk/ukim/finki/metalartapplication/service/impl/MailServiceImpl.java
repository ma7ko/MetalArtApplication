package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Mail;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import mk.ukim.finki.metalartapplication.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendVerifyEmail(RegistrationRequest request) throws MessagingException {

        Mail mail = new Mail();
        mail.setMailTo(request.getEmail());
        mail.setMailSubject("Account Verification");
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject(mail.getMailSubject());
        mimeMessageHelper.setFrom("metalartapplication.com");
        mimeMessageHelper.setTo(mail.getMailTo());

        Context context = new Context();
        context.setVariable("username", request.getUsername());

        String html = this.templateEngine.process("account-verification", context);
        mimeMessageHelper.setText(html, true);
        this.mailSender.send(mimeMessageHelper.getMimeMessage());

    }

}
