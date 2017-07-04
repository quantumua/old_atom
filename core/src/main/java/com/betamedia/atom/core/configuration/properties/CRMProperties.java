package com.betamedia.atom.core.configuration.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public class CRMProperties<T extends EnvironmentDependent> {
    private String mobileUrl;
    private String url;
    private String backOfficeUsername;
    private String backOfficePassword;
    private String backOfficeVersionUrl;

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
