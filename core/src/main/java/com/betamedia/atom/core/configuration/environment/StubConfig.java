package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.persistence.repositories.*;
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
    public static final String DB_ENABLED_PROPERTY = "db.enabled";
    public static final String GIGASPACES_ENABLED_PROPERTY = "gigaspaces.enabled";

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

    @Configuration
    @ConditionalOnProperty(name = DB_ENABLED_PROPERTY, havingValue = "false")
    public static class StubRepositoriesConfig {

        @Bean
        public AbstractConnectionBaseRepository connectionBaseRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractConnectionBaseRepository.class);
        }
        @Bean
        public AbstractConnectionRoleBaseRepository connectionRoleBaseRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractConnectionRoleBaseRepository.class);
        }

        @Bean
        public AbstractContactBaseRepository contactBaseRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractContactBaseRepository.class);
        }

        @Bean
        public AbstractContactExtensionRepository contactExtensionRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractContactExtensionRepository.class);
        }

        @Bean
        public AbstractRiskLimitsRepository riskLimitsRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractRiskLimitsRepository.class);
        }

        @Bean
        public AbstractTrackingInfoExtensionRepository trackingInfoExtensionRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractTrackingInfoExtensionRepository.class);
        }

        @Bean
        public AbstractTrackingInfoRepository trackingInfoRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractTrackingInfoRepository.class);
        }

        @Bean
        public AbstractTradingAccountExtensionRepository accountExtensionRepository() throws InstantiationException, IllegalAccessException {
            return getStub(AbstractTradingAccountExtensionRepository.class);
        }

    }

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

}
