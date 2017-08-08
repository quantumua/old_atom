package com.betamedia.atom.core.fwtestrunner.environment.initializer;

import com.betamedia.atom.core.product.ProductDependent;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 7/3/17.
 */
public interface TestRunningEnvInitializer extends ProductDependent {

    void initializeEnvironment(Properties properties);

}
