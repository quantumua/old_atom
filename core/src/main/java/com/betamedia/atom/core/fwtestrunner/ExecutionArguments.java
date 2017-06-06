package com.betamedia.atom.core.fwtestrunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author mbelyaev
 * @since 6/6/17
 */
public class ExecutionArguments {
    public final Properties properties;
    public final String reportDirectory;
    public final String reportFile;
    public final LocalDateTime time;
    public final List<String> suites;

    public ExecutionArguments(Properties properties, List<String> suites) {
        this.properties = properties;
        this.time = LocalDateTime.now();
        this.suites = suites;
        this.reportDirectory = getReportPath(properties);
        this.reportFile = reportDirectory + "/index.html";
    }

    private String getReportPath(Properties props) {
        return TestRunnerHandler.TEST_OUTPUT_DIRECTORY + (time.toString() + "." + Objects.hash(props, time, Thread.currentThread())).replaceAll("[^a-zA-Z0-9]", "_");
    }
}
