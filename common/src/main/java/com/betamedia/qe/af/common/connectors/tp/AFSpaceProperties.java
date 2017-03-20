package com.betamedia.qe.af.common.connectors.tp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AFSpaceProperties {

    @Value("${af.tp.spaceLocators}")
    private String afSpaceLocators;

    @Value("${af.tp.spaceUsername}")
    private String afSpaceUsername;

    @Value("${af.tp.spacePassword}")
    private String afSpacePassword;

    @Value("${af.tp.spaceGroups}")
    private String afSpaceGroups;

    @Value("${af.tp.spaceUrl}")
    private String afSpaceURL;

    @Value("${af.tp.username}")
    private String afUsername;

    @Value("${af.tp.pwd}")
    private String afPwd;

    public String getAfSpaceLocators() {
        return afSpaceLocators;
    }

    public String getAfSpaceUsername() {
        return afSpaceUsername;
    }

    public String getAfSpacePassword() {
        return afSpacePassword;
    }

    public String getAfSpaceGroups() {
        return afSpaceGroups;
    }

    public String getAfSpaceURL() {
        return afSpaceURL;
    }

    public String getAfUsername() {
        return afUsername;
    }

    public String getAfPwd() {
        return afPwd;
    }

}
