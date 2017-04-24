package com.betamedia.qe.af.core.environment.tp;

import com.betamedia.qe.af.core.dsl.pages.type.EnvironmentType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public interface QAEnvironment extends EnvironmentDependent {

    @Override
    default EnvironmentType getEnvironment() {
        return EnvironmentType.QA;
    }
}
