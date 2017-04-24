package com.betamedia.qe.af.core.dsl.pages.type;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public enum EnvironmentType {

    QA("qa"),
    PROD("prod"),
    AUTOMATION("automation");

    private String value;

    EnvironmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EnvironmentType parse(String name) {
        for (EnvironmentType value : values()) {
            if (value.getValue().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "No EnvironmentType constant for " + name);
    }
}
