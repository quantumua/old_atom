package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import java.text.MessageFormat;

/**
 * Created by sergeyg on 05.07.17.
 */
public class TestCaseResult {

    private final int buildId;
    private final int planId;
    private final String displayId;
    private final String configuration;
    private final String parameters;
    private final String log;
    private final ExecutionStatus status;
    private final String stacktrace;

    public TestCaseResult(int buildId, int planId, String displayId, String configuration, String parameters, String log, ExecutionStatus status, String stacktrace) {
        this.buildId = buildId;
        this.planId = planId;
        this.displayId = displayId;
        this.configuration = configuration;
        this.parameters = parameters;
        this.log = log;
        this.status = status;
        this.stacktrace = stacktrace;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getPlanId() {
        return planId;
    }

    public String getNotes() {
        return MessageFormat.format("Configuration:\n{0}\nParameters:\n{1}\nLog:\n{2}\nStacktrace:\n{3}", configuration, parameters, log, stacktrace);
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public String getDisplayId() {
        return displayId;
    }

    @Override
    public String toString() {
        return "TestCaseResult{" +
                "buildId=" + buildId +
                ", planId=" + planId +
                ", displayId='" + displayId + '\'' +
                ", configuration='" + configuration + '\'' +
                ", parameters='" + parameters + '\'' +
                ", log='" + log + '\'' +
                ", status=" + status +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }
}
