package com.betamedia.atom.core.fwtestrunner.environment.configuration;


import com.betamedia.atom.core.dsl.type.ProductType;

/**
 * @author mbelyaev
 * @since 8/7/17
 */
@FunctionalInterface
public interface TestConfigurationPropertiesParserProvider {
    TestConfigurationPropertiesParser get(ProductType productType);
}
