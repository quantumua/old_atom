package com.betamedia.atom.core.fwtestrunner.environment.configuration;

import com.betamedia.atom.core.product.ProductDependent;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;

import java.util.Properties;

/**
 * @author mbelyaev
 * @since 8/7/17
 */
public interface TestConfigurationPropertiesParser extends ProductDependent {
    Properties parse(TestConfigurationProperties configuration);
}
