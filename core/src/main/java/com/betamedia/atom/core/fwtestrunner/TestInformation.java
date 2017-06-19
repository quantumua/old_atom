package com.betamedia.atom.core.fwtestrunner;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class TestInformation {
    public final UUID id;
    public final Boolean isContinuous;
    public final String name;
    public final String cronExpression;
    public final List<UUID> childTaskIds;
    public final Status status;
    public final Boolean hasFailed;
    public final LocalDateTime time;
    public final Properties properties;
    public final List<String> suites;
    public final String reportDirectory;
    public final String reportURL;
    public final List<String> attachmentURLs;
    public final String emailReportURL;
    private final TestInformationHandler handler;

    private TestInformation(UUID id, Boolean isContinuous, String name, String cronExpression, List<UUID> childTaskIds, Status status, Boolean hasFailed, LocalDateTime time, Properties properties, List<String> suites, String reportDirectory, String reportURL, List<String> attachmentURLs, String emailReportURL, TestInformationHandler handler) {
        this.id = id;
        this.isContinuous = isContinuous;
        this.name = name;
        this.cronExpression = cronExpression;
        this.childTaskIds = childTaskIds;
        this.status = status;
        this.hasFailed = hasFailed;
        this.time = time;
        this.properties = properties;
        this.suites = suites;
        this.reportDirectory = reportDirectory;
        this.reportURL = reportURL;
        this.attachmentURLs = attachmentURLs;
        this.emailReportURL = emailReportURL;
        this.handler = handler;
    }

    public static TestInformationBuilder builder(TestInformationHandler handler) {
        return new TestInformationBuilder(handler);
    }

    public TestInformationBuilder update() {
        return new TestInformationBuilder(this);
    }

    public static class TestInformationBuilder {
        private UUID id;
        private Boolean isContinuous;
        private String name;
        private String cronExpression;
        private List<UUID> childTaskIds;
        private Status status;
        private Boolean hasFailed;
        private LocalDateTime time;
        private Properties properties;
        private List<String> suites;
        private String reportDirectory;
        private String reportURL;
        private List<String> attachmentURLs;
        private String emailReportURL;
        private final TestInformationHandler handler;

        private TestInformationBuilder(TestInformationHandler handler) {
            this.id = UUID.randomUUID();
            this.time = LocalDateTime.now();
            this.handler = handler;
        }

        private TestInformationBuilder(TestInformation source) {
            TestInformation currentSource = source.handler.get(source.id);
            this.id = currentSource.id;
            this.isContinuous = currentSource.isContinuous;
            this.childTaskIds = currentSource.childTaskIds;
            this.status = currentSource.status;
            this.hasFailed = currentSource.hasFailed;
            this.time = currentSource.time;
            this.properties = currentSource.properties;
            this.suites = currentSource.suites;
            this.reportDirectory = currentSource.reportDirectory;
            this.reportURL = currentSource.reportURL;
            this.attachmentURLs = currentSource.attachmentURLs;
            this.emailReportURL = currentSource.emailReportURL;
            this.handler = currentSource.handler;
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

        public TestInformationBuilder withChildTasks(List<UUID> childTaskIds) {
            this.childTaskIds = childTaskIds;
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

        public TestInformationBuilder addProperty(String key, String value) {
            if (this.properties == null) this.properties = new Properties();
            this.properties.setProperty(key, value);
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

        public TestInformation build() {
            if (isContinuous && childTaskIds == null) this.childTaskIds = new ArrayList<>();
            if (this.reportDirectory == null) this.reportDirectory = getReportDirectory(properties, time);
            return store(new TestInformation(id, isContinuous, name, cronExpression, childTaskIds, status, hasFailed, time, properties, suites, reportDirectory, reportURL, attachmentURLs, emailReportURL, handler));
        }

        private static String getReportDirectory(Properties props, LocalDateTime time) {
            return TestRunnerHandler.TEST_OUTPUT_DIRECTORY + (time.toString() + "." + Objects.hash(props, time, Thread.currentThread())).replaceAll("[^a-zA-Z0-9]", "_");
        }

        private TestInformation store(TestInformation testInformation) {
            handler.put(testInformation);
            return testInformation;
        }
    }

    public enum Status {
        CREATED,
        RUNNING,
        ABORTED,
        COMPLETED;
    }
}
