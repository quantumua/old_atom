package com.betamedia.qe.af.core.connectors.tp.feedgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/20/17.
 */
@Component
public class AFFeedGatewayProperties {
    @Value("${af.gw.spaceLocators}")
    private String afSpaceLocators;

    @Value("${af.gw.spaceUsername}")
    private String afSpaceUsername;

    @Value("${af.gw.spacePassword}")
    private String afSpacePassword;

    @Value("${af.gw.spaceGroups}")
    private String afSpaceGroups;

    @Value("${af.gw.spaceUrl}")
    private String afSpaceURL;

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

}
