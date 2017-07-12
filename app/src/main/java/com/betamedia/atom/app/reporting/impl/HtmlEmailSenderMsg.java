package com.betamedia.atom.app.reporting.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/28/17.
 */
@Component
public class HtmlEmailSenderMsg {

    @Autowired
    private Environment environment;

    private String userName;
    private Session session;


    @PostConstruct
    public void init() {
        String host = environment.getRequiredProperty("spring.mail.host");
        String port =  environment.getRequiredProperty("spring.mail.port");
        userName = environment.getRequiredProperty("spring.mail.username");
//        TODO: when run on autolin1 there is no spring.mail.password property in environment!!!. Temporary hardcoded.
        String password =
                //"4zp5hYBS";
                environment.getRequiredProperty("spring.mail.password");
        String smtpAuth =
                //"true";
                environment.getRequiredProperty("spring.mail.properties.mail.smtp.auth");
        String smtpStarttls =
                //"true";
                environment.getRequiredProperty("spring.mail.properties.mail.smtp.starttls.enable");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", smtpStarttls);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        session = Session.getInstance(properties, auth);
    }

    public void sendHtmlEmail(String toAddress,
                              String subject, String message, String[] attachFiles) throws MessagingException {


        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }
}