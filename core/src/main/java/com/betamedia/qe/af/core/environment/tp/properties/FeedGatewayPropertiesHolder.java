package com.betamedia.qe.af.core.environment.tp.properties;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public interface FeedGatewayPropertiesHolder<T extends EnvironmentDependent> {

    String getGWSpaceLocators();

    String getGWSpaceUsername();

    String getGWSpacePassword();

    String getGWSpaceGroups();

    String getGWSpaceURL();
}
