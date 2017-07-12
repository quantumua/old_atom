package com.betamedia.atom.app.types;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/14/17.
 */
public enum TestRunnerType {
    TESTNG("testng");

    private String value;

    TestRunnerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TestRunnerType parse(String name) {
        for (TestRunnerType value : values()) {
            if (value.getValue().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "No TestRunnerType constant for " + name);
    }


}
