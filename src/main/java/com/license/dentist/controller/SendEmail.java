//package com.license.dentist.controller;
//
//import javax.mail.*;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Properties;
//
//public class SendEmail {
//    public void sendmail(String email, String date, String subject, String doctor, String content) throws AddressException, MessagingException, IOException {
//        String senderUserName = "46c8a908952464";
//        String senderPassword = "b632636814756d";
//        String serverAddress = "smtp.mailtrap.io";
//        String serverPort = "2525";
//
//        Properties properties = new Properties();
//
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.debug", "true");
//        properties.put("mail.smtp.ssl.trust", serverAddress);
//        properties.put("mail.protocol.ssl.trust", serverAddress);
//        properties.put("mail.properties.mail.smtp.ssl.trust", serverAddress);
//        properties.put("mail.smtp.host", serverAddress);
//        properties.put("mail.smtp.port", serverPort);
//
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(senderUserName, senderPassword);
//            }
//        });
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress(email, false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dentaLUX@yahoo.com"));
//        msg.setSubject(subject);
//        msg.setContent(content, "text/html");
//        msg.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("Tutorials point email", "text/html");
//
////
//        Transport.send(msg);
//    }
//}
