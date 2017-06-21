package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.fwtestrunner.TestInformationHandler;
import com.betamedia.atom.core.fwtestrunner.scheduling.ContinuousTestFactory;
import com.betamedia.atom.core.fwtestrunner.scheduling.RepeatingTest;
import com.betamedia.atom.core.fwtestrunner.scheduling.ScheduledTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@Configuration
public class ContinuousTestFactoryConfig {
    @Bean
    public ContinuousTestFactory repeatingTaskFactory(TaskExecutor asyncTaskExecutor, TestInformationHandler handler) {
        return (testTask, testExecution) -> new RepeatingTest(testTask, testExecution, handler::put, asyncTaskExecutor);
    }

    @Bean
    public ContinuousTestFactory scheduledTaskFactory(TaskScheduler taskScheduler, TestInformationHandler handler) {
        return (testTask, testExecution) -> new ScheduledTest(testTask, testExecution, handler::put, taskScheduler);
    }
}
