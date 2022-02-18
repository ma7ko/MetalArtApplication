package mk.ukim.finki.metalartapplication.service.impl;

import mk.ukim.finki.metalartapplication.model.Mail;
import mk.ukim.finki.metalartapplication.model.dto.RegistrationRequest;
import mk.ukim.finki.metalartapplication.service.MailService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.apache.commons.io.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
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

    @Override
    public void sendSketchEmail(MultipartFile uploadedFile, String name, String description, Double width, Double height, Double depth, String metric) throws MessagingException, IOException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        FileUtils.writeByteArrayToFile(new File("D:\\Desktop\\file.svg"), uploadedFile.getBytes());
        mimeMessageHelper.setSubject("Product Request");
        mimeMessageHelper.setFrom("markopanuskovski@yahoo.com");
        mimeMessageHelper.setTo("markopanuskovski@gmail.com");
        FileSystemResource fsr = new FileSystemResource(new File("D:\\Desktop\\file.svg"));
        mimeMessageHelper.addAttachment(fsr.getFilename(), fsr);

        Context context = new Context();
        context.setVariable("title", name);
        context.setVariable("description", description);
        context.setVariable("width", width);
        context.setVariable("height", height);
        context.setVariable("depth", depth);
        context.setVariable("metric", metric);

        String html = this.templateEngine.process("product-request", context);
        mimeMessageHelper.setText(html, true);
        this.mailSender.send(mimeMessageHelper.getMimeMessage());
    }

}
