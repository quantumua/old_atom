package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.StubMethod;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This configuration creates stub implementations of GigaSpaces connectors that will be injected into other components
 * instead of real ones. Enable with <code>gigaspaces.value=false</code> property.
 *
 * @author mbelyaev
 * @since 7/5/17
 */
@Configuration
public class StubConfig {
    public static final String GIGASPACES_ENABLED_PROPERTY = "gigaspaces.enabled";

    private static <T> T getStub(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return new ByteBuddy()
                .subclass(clazz)
                .method(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class)))
                .intercept(StubMethod.INSTANCE)
                .make()
                .load(clazz.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .newInstance();
    }

    @Configuration
    @ConditionalOnProperty(name = GIGASPACES_ENABLED_PROPERTY, havingValue = "false")
    public static class StubGigaSpacesConfig {
        @Bean
        public FWFeedGatewayConnector stubFeedGWConnector() throws InstantiationException, IllegalAccessException {
            return getStub(FWFeedGatewayConnector.class);
        }

        @Bean
        public FWTPConnector stubFWTPConnector() throws InstantiationException, IllegalAccessException {
            return getStub(FWTPConnector.class);
        }

    }

}
