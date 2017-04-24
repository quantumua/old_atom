package com.betamedia.qe.af.core.environment.tp.properties;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public interface EntityPropertiesHolder<T extends EnvironmentDependent> {
    String getDefaultAccountGroupId();
    String getBrandId();
}
