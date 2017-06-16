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
    @Bean
    public TestTaskCompletionListener emailOnFailedTestListener(EmailService emailService) {
        return task -> {
            if (task.hasFailed && task.properties.getProperty("email.to") != null && !task.properties.getProperty("email.to").isEmpty()) {
                emailService.sendLocalFile(
                        task.properties.getProperty("email.to"),
                        task.properties.getProperty("email.subject"),
                        task.emailReportURL,
                        task.attachmentURLs);
            }
        };
    }
}
