package com.betamedia.atom.core.configuration.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public class CRMProperties<T extends EnvironmentDependent> {
    private String mobileCrmUrl;
    private String crmUrl;
    private String backOfficeUsername;
    private String backOfficePassword;
    private String backOfficeVersionUrl;

    public String getMobileCrmUrl() {
        return mobileCrmUrl;
    }

    public void setMobileCrmUrl(String mobileCrmUrl) {
        this.mobileCrmUrl = mobileCrmUrl;
    }

    public String getCrmUrl() {
        return crmUrl;
    }

    public void setCrmUrl(String crmUrl) {
        this.crmUrl = crmUrl;
    }

    public String getBackOfficeUsername() {
        return backOfficeUsername;
    }

    public void setBackOfficeUsername(String backOfficeUsername) {
        this.backOfficeUsername = backOfficeUsername;
    }

    public String getBackOfficePassword() {
        return backOfficePassword;
    }

    public void setBackOfficePassword(String backOfficePassword) {
        this.backOfficePassword = backOfficePassword;
    }

    public String getBackOfficeVersionUrl() {
        return backOfficeVersionUrl;
    }

    public void setBackOfficeVersionUrl(String backOfficeVersionUrl) {
        this.backOfficeVersionUrl = backOfficeVersionUrl;
    }
}
