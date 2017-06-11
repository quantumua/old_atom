package com.betamedia.qe.af.core.testlink;

public enum TestCaseResultStatus {

    PASSED("p"),
    FAILED("f"),
    BLOCKED("b");

    private String status;

    TestCaseResultStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
