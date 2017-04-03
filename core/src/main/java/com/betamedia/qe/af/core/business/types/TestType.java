package com.betamedia.qe.af.core.business.types;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/14/17.
 */
public enum TestType {
    TESTNG("testng");

    private String value;

    TestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TestType parse(String name) {
        for (TestType value : values()) {
            if (value.getValue().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "No TestType constant for " + name);
    }


}
