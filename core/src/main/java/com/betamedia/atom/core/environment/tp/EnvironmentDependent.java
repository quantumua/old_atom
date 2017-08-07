package com.betamedia.atom.core.environment.tp;

import com.betamedia.atom.core.dsl.type.EnvironmentType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public interface EnvironmentDependent {
    EnvironmentType getEnvironment();
}
