package com.betamedia.qe.af.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
@Configuration
public class ExecutorConfiguration {

    private final int RUNNER_THREADS_NUMBER = 10;

    @Bean
    public Executor runnerTaskExecutor() {
        return getThreadPoolTaskExecutor(RUNNER_THREADS_NUMBER, Thread.MAX_PRIORITY);
    }


    private Executor getThreadPoolTaskExecutor(int poolSize, int priority) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadPriority(priority);
        executor.initialize();
        return executor;
    }
}
