package com.betamedia.atom.app.reporting.impl;

import com.betamedia.atom.app.reporting.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author mbelyaev
 * @since 4/25/17
 */
@Component
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    @Autowired
    private HtmlEmailSenderMsg emailSender;

    @Override
    public void sendLocalFile(String to, String subject, String contentPath, List<String> attachmentPaths) {
        try {
            emailSender.sendHtmlEmail(to, subject, getContent(contentPath), attachmentPaths.toArray(new String[attachmentPaths.size()]));
        } catch (MessagingException | IOException e) {
            logger.error("", e);
        }
    }

    private String getContent(String pathToFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathToFile)));
    }
}
