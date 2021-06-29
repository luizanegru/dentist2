package com.license.dentist.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/email")
public class EmailController {
    @RequestMapping(value = "/sendemail")
    public String sendEmail(String email, String subject,  String content) throws IOException, MessagingException {
        sendmail(email,  subject,   content);
        return "Email sent successfully";
    }

    private void sendmail(String email, String subject,  String content) throws AddressException, MessagingException, IOException {
        String senderUserName = "46c8a908952464";
        String senderPassword = "b632636814756d";
        String serverAddress = "smtp.mailtrap.io";
        String serverPort = "2525";

        Properties properties = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", serverAddress);
        properties.put("mail.protocol.ssl.trust", serverAddress);
        properties.put("mail.properties.mail.smtp.ssl.trust", serverAddress);
        properties.put("mail.smtp.host", serverAddress);
        properties.put("mail.smtp.port", serverPort);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderUserName, senderPassword);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("dentalLUX@yahoo.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject(subject);
        msg.setContent(content, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }
}
