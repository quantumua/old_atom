package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * QA Environment-specific bean configuration class.
 *
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("qa")
public class QAEnvironmentConfig {

    public static EnvironmentType getEnvironment() {
        return EnvironmentType.QA;
    }

    @Bean
    public AbstractApplicationVersionService<QAEnvironment> qaVersionService() {
        return new AbstractApplicationVersionService<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<QAEnvironment> qaTPTemplate() {
        return new AbstractTPTemplate<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<QAEnvironment> qaAccountOperations() {
        return new AbstractAccountOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<QAEnvironment> qaBonusOperations() {
        return new AbstractBonusOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<QAEnvironment> qaCustomerOperations() {
        return new AbstractCustomerOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<QAEnvironment> qaOnBoardingOperations() {
        return new AbstractOnBoardingOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCrmDBOperations<QAEnvironment> qaCrmDBOperations() {
        return new AbstractCrmDBOperations<QAEnvironment>() {

            @Override
            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

}