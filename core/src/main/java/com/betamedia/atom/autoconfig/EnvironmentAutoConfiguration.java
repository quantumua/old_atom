package com.betamedia.atom.autoconfig;

import com.betamedia.atom.autoconfig.environment.AutomationEnvironmentConfig;
import com.betamedia.atom.autoconfig.environment.NewAutomationEnvironmentConfig;
import com.betamedia.atom.autoconfig.environment.QAEnvironmentConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author mbelyaev
 * @since 7/20/17
 */
@Configuration
@Import({
        AutomationEnvironmentConfig.class,
        NewAutomationEnvironmentConfig.class,
        QAEnvironmentConfig.class
})
@AutoConfigureAfter(StubAutoConfiguration.class)
public class EnvironmentAutoConfiguration {
}
