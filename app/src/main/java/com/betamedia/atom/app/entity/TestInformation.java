package com.betamedia.atom.app.entity;

import com.betamedia.atom.app.handler.TestHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

/**
 * Entity to encapsulate necessary test execution information and metadata.
 *
 * @author mbelyaev
 * @since 6/16/17
 */
public class TestInformation {
    public final UUID id;
    public final Boolean isContinuous;
    public final String name;
    public final String cronExpression;
    public final Status status;
    public final Boolean hasFailed;
    public final LocalDateTime time;
    public final Properties properties;
    public final List<String> suites;
    public final String reportDirectory;
    public final String reportURL;
    public final List<String> attachmentURLs;
    public final String emailReportURL;
    public final Throwable cause;

    private TestInformation(UUID id, Boolean isContinuous, String name, String cronExpression, Status status, Boolean hasFailed, LocalDateTime time, Properties properties, List<String> suites, String reportDirectory, String reportURL, List<String> attachmentURLs, String emailReportURL, Throwable cause) {
        this.id = id;
        this.isContinuous = isContinuous;
        this.name = name;
        this.cronExpression = cronExpression;
        this.status = status;
        this.hasFailed = hasFailed;
        this.time = time;
        this.properties = properties;
        this.suites = suites;
        this.reportDirectory = reportDirectory;
        this.reportURL = reportURL;
        this.attachmentURLs = attachmentURLs;
        this.emailReportURL = emailReportURL;
        this.cause = cause;
    }

    public static TestInformationBuilder builder() {
        return new TestInformationBuilder();
    }

    public TestInformationBuilder update() {
        return new TestInformationBuilder(this);
    }

    public static class TestInformationBuilder {
        private UUID id;
        private Boolean isContinuous = false;
        private String name;
        private String cronExpression;
        private Status status;
        private Boolean hasFailed;
        private LocalDateTime time;
        private Properties properties;
        private List<String> suites;
        private String reportDirectory;
        private String reportURL;
        private List<String> attachmentURLs;
        private String emailReportURL;
        private Throwable cause;

        private TestInformationBuilder() {
            this.id = UUID.randomUUID();
            this.time = LocalDateTime.now();
        }

        private TestInformationBuilder(TestInformation source) {
            this.id = source.id;
            this.name = source.name;
            this.isContinuous = source.isContinuous;
            this.cronExpression = source.cronExpression;
            this.status = source.status;
            this.hasFailed = source.hasFailed;
            this.time = source.time;
            this.properties = source.properties;
            this.suites = source.suites;
            this.reportDirectory = source.reportDirectory;
            this.reportURL = source.reportURL;
            this.attachmentURLs = source.attachmentURLs;
            this.emailReportURL = source.emailReportURL;
            this.cause = source.cause;
        }

        public TestInformationBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TestInformationBuilder isContinuous(boolean isContinuous) {
            this.isContinuous = isContinuous;
            return this;
        }

        public TestInformationBuilder withCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
            return this;
        }

        public TestInformationBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TestInformationBuilder hasFailed(Boolean hasFailed) {
            this.hasFailed = hasFailed;
            return this;
        }

        public TestInformationBuilder withProperties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public TestInformationBuilder withReportURL(String reportURL) {
            this.reportURL = reportURL;
            return this;
        }

        public TestInformationBuilder withSuites(List<String> suites) {
            this.suites = suites;
            return this;
        }

        public TestInformationBuilder withAttachmentURLs(List<String> attachmentURLs) {
            this.attachmentURLs = attachmentURLs;
            return this;
        }

        public TestInformationBuilder withEmailReportURL(String emailReportURL) {
            this.emailReportURL = emailReportURL;
            return this;
        }

        public TestInformationBuilder withTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public TestInformationBuilder withCause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public TestInformationBuilder updateReportDirectory() {
            this.reportDirectory = TestHandler.TEST_OUTPUT_DIRECTORY +
                    (time.toString() + "." + Objects.hash(properties, time, Thread.currentThread()))
                            .replaceAll("[^a-zA-Z0-9]", "_");
            return this;
        }

        public TestInformation build() {
            return new TestInformation(id, isContinuous, name, cronExpression, status, hasFailed, time, properties, suites, reportDirectory, reportURL, attachmentURLs, emailReportURL, cause);
        }
    }

    public enum Status {
        CREATED,
        RUNNING,
        ABORTED,
        COMPLETED, FAILED;
    }
}
