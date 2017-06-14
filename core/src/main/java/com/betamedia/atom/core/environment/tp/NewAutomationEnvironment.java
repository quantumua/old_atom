package com.betamedia.atom.core.environment.tp;

import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;

/**
 * @author mbelyaev
 * @since 6/13/17
 */
public interface NewAutomationEnvironment extends EnvironmentDependent {

    @Override
    default EnvironmentType getEnvironment() {
        return EnvironmentType.NEW_AUTOMATION;
    }
}
