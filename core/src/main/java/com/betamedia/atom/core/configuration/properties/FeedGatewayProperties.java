package com.betamedia.atom.core.configuration.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author mbelyaev
 * @since 7/4/17
 */
public class FeedGatewayProperties<T extends EnvironmentDependent> {
    private String locators;
    private String spaceUsername;
    private String spacePassword;
    private String groups;
    private String spaceUrl;

    public String getLocators() {
        return locators;
    }

    public void setLocators(String locators) {
        this.locators = locators;
    }

    public String getSpaceUsername() {
        return spaceUsername;
    }

    public void setSpaceUsername(String spaceUsername) {
        this.spaceUsername = spaceUsername;
    }

    public String getSpacePassword() {
        return spacePassword;
    }

    public void setSpacePassword(String spacePassword) {
        this.spacePassword = spacePassword;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getSpaceUrl() {
        return spaceUrl;
    }

    public void setSpaceUrl(String spaceUrl) {
        this.spaceUrl = spaceUrl;
    }

}
