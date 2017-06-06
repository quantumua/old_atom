package com.betamedia.atom.core.environment.tp;

import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public interface AutomationEnvironment extends EnvironmentDependent {

    @Override
    default EnvironmentType getEnvironment() {
        return EnvironmentType.AUTOMATION;
    }
}
