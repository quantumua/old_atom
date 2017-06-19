package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.holders.AppContextHolder;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class TestTask {
    public final UUID uuid;
    public final Boolean isContinuous;
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

    private TestTask(UUID uuid, Boolean isContinuous, List<UUID> childTaskIds, Status status, Boolean hasFailed, LocalDateTime time, Properties properties, List<String> suites, String reportDirectory, String reportURL, List<String> attachmentURLs, String emailReportURL) {
        this.uuid = uuid;
        this.isContinuous = isContinuous;
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
    }

    public static TaskStatusBuilder builder() {
        return new TaskStatusBuilder();
    }

    public TaskStatusBuilder update() {
        return new TaskStatusBuilder(this);
    }

    public static class TaskStatusBuilder {
        private UUID uuid;
        private Boolean isContinuous;
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

        private TaskStatusBuilder() {
            this.uuid = UUID.randomUUID();
            this.time = LocalDateTime.now();
        }

        private TaskStatusBuilder(TestTask source) {
            this.uuid = source.uuid;
            this.isContinuous = source.isContinuous;
            this.childTaskIds = source.childTaskIds;
            this.status = source.status;
            this.hasFailed = source.hasFailed;
            this.time = source.time;
            this.properties = source.properties;
            this.suites = source.suites;
            this.reportDirectory = source.reportDirectory;
            this.reportURL = source.reportURL;
            this.attachmentURLs = source.attachmentURLs;
            this.emailReportURL = source.emailReportURL;
        }

        public TaskStatusBuilder isContinuous(boolean isContinuous) {
            this.isContinuous = isContinuous;
            return this;
        }

        public TaskStatusBuilder withChildTasks(List<UUID> childTaskIds) {
            this.childTaskIds = childTaskIds;
            return this;
        }

        public TaskStatusBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TaskStatusBuilder hasFailed(Boolean hasFailed) {
            this.hasFailed = hasFailed;
            return this;
        }

        public TaskStatusBuilder withProperties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public TaskStatusBuilder addProperty(String key, String value) {
            if (this.properties == null) this.properties = new Properties();
            this.properties.setProperty(key, value);
            return this;
        }

        public TaskStatusBuilder withReportURL(String reportURL) {
            this.reportURL = reportURL;
            return this;
        }

        public TaskStatusBuilder withSuites(List<String> suites) {
            this.suites = suites;
            return this;
        }

        public TaskStatusBuilder withAttachmentURLs(List<String> attachmentURLs) {
            this.attachmentURLs = attachmentURLs;
            return this;
        }

        public TaskStatusBuilder withEmailReportURL(String emailReportURL) {
            this.emailReportURL = emailReportURL;
            return this;
        }

        public TestTask build() {
            if (isContinuous && childTaskIds == null) this.childTaskIds = new ArrayList<>();
            if (this.reportDirectory == null) this.reportDirectory = getReportDirectory(properties, time);
            return store(new TestTask(uuid, isContinuous, childTaskIds, status, hasFailed, time, properties, suites, reportDirectory, reportURL, attachmentURLs, emailReportURL));
        }

        private static TestTask store(TestTask task) {
            AppContextHolder.getBean(TestTaskHandler.class).put(task);
            return task;
        }

        private static String getReportDirectory(Properties props, LocalDateTime time) {
            return TestRunnerHandler.TEST_OUTPUT_DIRECTORY + (time.toString() + "." + Objects.hash(props, time, Thread.currentThread())).replaceAll("[^a-zA-Z0-9]", "_");
        }
    }

    public enum Status {
        CREATED,
        STARTED,
        ABORTED,
        COMPLETED;
    }
}
