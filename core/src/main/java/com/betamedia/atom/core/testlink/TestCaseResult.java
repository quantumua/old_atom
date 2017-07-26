package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import java.text.MessageFormat;

/**
 * Created by sergeyg on 05.07.17.
 */
public class TestCaseResult {

    private int buildId;
    private int planId;
    private String parameters;
    private ExecutionStatus status;
    private String displayId;
    private String log;

    public TestCaseResult(int buildId, int planId, String displayId, String parameters, String log,
                          ExecutionStatus status) {
        this.buildId = buildId;
        this.planId = planId;
        this.parameters = parameters;
        this.log = log;
        this.status = status;
        this.displayId = displayId;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getPlanId() {
        return planId;
    }

    public String getNotes() {
        return MessageFormat.format("Parameters: {0}\nLog:\n{1}", parameters, log);
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
                ", parameters='" + parameters + '\'' +
                ", status=" + status +
                ", displayId='" + displayId + '\'' +
                ", log='" + log + '\'' +
                '}';
    }
}
