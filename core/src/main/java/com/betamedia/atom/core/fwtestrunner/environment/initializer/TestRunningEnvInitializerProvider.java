package com.betamedia.atom.core.fwtestrunner.environment.initializer;


import com.betamedia.atom.core.dsl.type.ProductType;

/**
 * @author mbelyaev
 * @since 8/7/17
 */
@FunctionalInterface
public interface TestRunningEnvInitializerProvider {
    TestRunningEnvInitializer get(ProductType productType);
}
