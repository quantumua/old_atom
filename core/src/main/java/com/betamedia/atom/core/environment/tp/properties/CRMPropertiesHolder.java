package com.betamedia.atom.core.environment.tp.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public interface CRMPropertiesHolder<T extends EnvironmentDependent> {

    String getMobileCRMUrl();

    String getCRMUrl();

    String getBackOffUsername();

    String getBackOffPwd();

    String getBackOffVersionURL();
}
