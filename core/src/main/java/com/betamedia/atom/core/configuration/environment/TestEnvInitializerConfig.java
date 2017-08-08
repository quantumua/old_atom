package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParser;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParserProvider;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializer;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializerProvider;
import com.betamedia.atom.core.product.ProductDependent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 8/7/17
 */
@Configuration
public class TestEnvInitializerConfig {
    @Bean
    public TestRunningEnvInitializerProvider intializerProvider(List<TestRunningEnvInitializer> initializers) {
        return collectToMap(initializers)::apply;
    }

    @Bean
    public TestConfigurationPropertiesParserProvider propertiesParserProvider(List<TestConfigurationPropertiesParser> parsers) {
        return collectToMap(parsers)::apply;
    }

    private <T extends ProductDependent> Function<ProductType, T> collectToMap(List<T> items) {
        return new Function<ProductType, T>() {
            Map<ProductType, T> map = items.stream().collect(Collectors.toMap(ProductDependent::getType, v -> v));

            @Override
            public T apply(ProductType productType) {
                return map.get(productType);
            }
        };
    }
}
