package com.betamedia.atom.app.config;

import com.betamedia.atom.app.scheduling.ContinuousTestFactory;
import com.betamedia.atom.app.scheduling.impl.ContinuousTest;
import com.betamedia.atom.app.scheduling.impl.RepeatingTest;
import com.betamedia.atom.app.scheduling.impl.ScheduledTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@Configuration
public class ContinuousTestFactoryConfig {
    @Bean
    public ContinuousTestFactory continuousTestFactory(TaskScheduler taskScheduler) {
        return (testExecution, cronExpression) -> cronExpression
                .map(c -> (ContinuousTest) new ScheduledTest(testExecution, c, taskScheduler))
                .orElseGet(() -> new RepeatingTest(testExecution));
    }

}
