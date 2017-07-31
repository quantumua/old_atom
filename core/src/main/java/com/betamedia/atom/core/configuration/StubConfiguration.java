package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.persistence.repositories.*;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.StubMethod;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mbelyaev
 * @since 7/18/17
 */
@Configuration
public class StubConfiguration {
    public static final String DB_ENABLED_PROPERTY = "db.enabled";

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
