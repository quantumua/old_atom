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

    private TestCaseResult(int buildId, int planId, String parameters,
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

    public static TestCaseResult.TestCaseResultBuilder builder(){
        return new TestCaseResult.TestCaseResultBuilder();
    }

    @Override
    public String toString() {
        return "TestCaseResult{" +
                "buildId=" + buildId +
                ", planId=" + planId +
                ", parameters='" + parameters + '\'' +
                ", status=" + status +
                ", displayId='" + displayId + '\'' +
                '}';
    }

    public static class TestCaseResultBuilder {
        private int buildId;
        private int planId;
        private String parameters;
        private ExecutionStatus status;
        private String displayId;

        public TestCaseResultBuilder setBuildId(int buildId) {
            this.buildId = buildId;
            return this;
        }
        public TestCaseResultBuilder setTestPlanId(int planId) {
            this.planId = planId;
            return this;
        }
        public TestCaseResultBuilder setParameters(String parameters) {
            this.parameters = parameters;
            return this;
        }
        public TestCaseResultBuilder setExecutionStatus(ExecutionStatus status) {
            this.status = status;
            return this;
        }
        public TestCaseResultBuilder setDisplayId(String displayId) {
            this.displayId = displayId;
            return this;
        }

        public TestCaseResult build() {
            return new TestCaseResult(buildId, planId, parameters, status, displayId);
        }
    }
}
