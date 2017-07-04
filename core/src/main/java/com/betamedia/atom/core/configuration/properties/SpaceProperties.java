package com.betamedia.atom.core.configuration.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public class SpaceProperties<T extends EnvironmentDependent> {
    private String locators;
    private String spaceUsername;
    private String spacePassword;
    private String groups;
    private String spaceUrl;
    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
