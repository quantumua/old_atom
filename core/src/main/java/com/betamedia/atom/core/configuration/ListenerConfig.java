package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import com.betamedia.atom.core.fwtestrunner.reporting.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
@Configuration
public class ListenerConfig {
    private static final String EMAIL_TO = "email.to";
    private static final String EMAIL_SUBJECT = "email.subject";

    @Bean
    public TestTaskCompletionListener emailOnFailedTestListener(EmailService emailService) {
        return task -> {
            if (task.hasFailed && task.properties.getProperty(EMAIL_TO) != null && !task.properties.getProperty(EMAIL_TO).isEmpty()) {
                emailService.sendLocalFile(
                        task.properties.getProperty(EMAIL_TO),
                        task.properties.getProperty(EMAIL_SUBJECT),
                        task.emailReportURL,
                        task.attachmentURLs);
            }
        };
    }
}
