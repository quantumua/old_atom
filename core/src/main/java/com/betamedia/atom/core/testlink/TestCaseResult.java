package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

/**
 * Created by sergeyg on 05.07.17.
 */
public class TestCaseResult {

    private int buildId;
    private int planId;
    private String parameters;
    private ExecutionStatus status;
    private String displayId;

    public TestCaseResult(int buildId, int planId, String parameters,
                           ExecutionStatus status, String displayId) {
        this.buildId = buildId;
        this.planId = planId;
        this.parameters = parameters;
        this.status = status;
        this.displayId = displayId;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getPlanId() {
        return planId;
    }

    public String getParameters() {
        return parameters;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public String getDisplayId() {
        return displayId;
    }
}
