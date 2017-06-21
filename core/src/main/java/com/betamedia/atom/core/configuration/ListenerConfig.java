package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.fwtestrunner.listeners.TestCompletionListener;
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
    public TestCompletionListener emailOnFailedTestListener(EmailService emailService) {
        return tests -> tests.stream()
                .filter(t -> t.hasFailed)
                .filter(t -> t.properties.getProperty(EMAIL_TO) != null && !t.properties.getProperty(EMAIL_TO).isEmpty())
                .forEach(t -> emailService.sendLocalFile(
                        t.properties.getProperty(EMAIL_TO),
                        t.properties.getProperty(EMAIL_SUBJECT),
                        t.emailReportURL,
                        t.attachmentURLs));
    }
}
