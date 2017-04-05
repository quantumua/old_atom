package com.betamedia.qe.af.core.fwtestrunner.types;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/14/17.
 */
public enum RunnerType {
    TESTNG("testng");

    private String value;

    RunnerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RunnerType parse(String name) {
        for (RunnerType value : values()) {
            if (value.getValue().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "No RunnerType constant for " + name);
    }


}
