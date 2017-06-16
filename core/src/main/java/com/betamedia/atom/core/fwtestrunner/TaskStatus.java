package com.betamedia.atom.core.fwtestrunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class TaskStatus {
    public final UUID uuid;
    public final Status status;
    public final Boolean hasFailed;
    public final LocalDateTime time;
    public final Properties properties;
    public final List<String> suites;
    public final String reportDirectory;
    public final String reportURL;
    public final List<String> attachmentPaths;
    public final String outputPath;

    private TaskStatus(UUID uuid, Status status, Boolean hasFailed, LocalDateTime time, Properties properties, List<String> suites, String reportDirectory, String reportURL, List<String> attachmentPaths, String outputPath) {
        this.uuid = uuid;
        this.status = status;
        this.hasFailed = hasFailed;
        this.time = time;
        this.properties = properties;
        this.suites = suites;
        this.reportDirectory = reportDirectory;
        this.reportURL = reportURL;
        this.attachmentPaths = attachmentPaths;
        this.outputPath = outputPath;
    }

    public static TaskStatusBuilder builder() {
        return new TaskStatusBuilder();
    }

    public TaskStatusBuilder update() {
        return new TaskStatusBuilder(this);
    }

    public static class TaskStatusBuilder {
        private UUID uuid;
        private Status status;
        private Boolean hasFailed;
        private LocalDateTime time;
        private Properties properties;
        private List<String> suites;
        private String reportDirectory;
        private String reportURL;
        private List<String> attachmentPaths;
        private String outputPath;

        private TaskStatusBuilder() {
            this.uuid = UUID.randomUUID();
        }

        private TaskStatusBuilder(TaskStatus source) {
            this.uuid = source.uuid;
            this.status = source.status;
            this.hasFailed = source.hasFailed;
            this.time = source.time;
            this.properties = source.properties;
            this.suites = source.suites;
            this.reportDirectory = source.reportDirectory;
            this.reportURL = source.reportURL;
            this.attachmentPaths = source.attachmentPaths;
            this.outputPath = source.outputPath;
        }

        public TaskStatusBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TaskStatusBuilder hasFailed(Boolean hasFailed) {
            this.hasFailed = hasFailed;
            return this;
        }

        public TaskStatusBuilder withTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public TaskStatusBuilder withProperties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public TaskStatusBuilder withSuites(List<String> suites) {
            this.suites = suites;
            return this;
        }

        public TaskStatusBuilder withReportDirectory(String reportDirectory) {
            this.reportDirectory = reportDirectory;
            return this;
        }

        public TaskStatusBuilder withReportURL(String reportURL) {
            this.reportURL = reportURL;
            return this;
        }

        public TaskStatusBuilder withAttachmentPaths(List<String> attachmentPaths) {
            this.attachmentPaths = attachmentPaths;
            return this;
        }

        public TaskStatusBuilder withOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public TaskStatus build() {
            return new TaskStatus(uuid, status, hasFailed, time, properties, suites, reportDirectory, reportURL, attachmentPaths, outputPath);
        }

    }

    public enum Status {
        IN_PROGRESS,
        ABORTED,
        COMPLETED,
        UNKNOWN;
    }
}
