package com.betamedia.qe.af.core.environment.tp.properties;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public interface SpacePropertiesHolder<T extends EnvironmentDependent> {

    String getSpaceLocators();

    String getSpaceUsername();

    String getSpacePassword();

    String getSpaceGroups();

    String getSpaceURL();

    String getUsername();

    String getPwd();
}
