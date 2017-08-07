package com.betamedia.atom.core.dsl.type;

/**
 * @author Maksym Tsybulskyy
 *         Date: 8/7/17.
 */
public enum TestConfigurationKeys {
    REMOTE_DRIVER_URL("remote.driver.url"),
    ENVIRONMENT_URL("environment.url"),
    BROWSER_TYPE("browser.type"),
    IMPLEMENTATION_VERSION("implementation.version"),
    REVISION_DATE("revision.date"),
    RUNNER_TYPE("runner.type"),
    ENVIRONMENT("environment");

    private String key;

    TestConfigurationKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static TestConfigurationKeys parse(String name) {
        for (TestConfigurationKeys value : values()) {
            if (value.getKey().equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "No EnvironmentType constant for " + name);
    }
}
