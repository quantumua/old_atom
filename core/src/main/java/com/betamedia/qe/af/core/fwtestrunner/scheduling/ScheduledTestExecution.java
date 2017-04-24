package com.betamedia.qe.af.core.fwtestrunner.scheduling;

import com.google.common.collect.ImmutableMap;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Created by mbelyaev on 4/19/17.
 */
public class ScheduledTestExecution implements TestExecution, ExecutionListener {

    private final AtomicBoolean isCompleted = new AtomicBoolean(true);
    private final Consumer<ExecutionListener> consumer;
    private final String cronExpression;
    private final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();


    public ScheduledTestExecution(Consumer<ExecutionListener> consumer, String cronExpression) {
        this.consumer = consumer;
        this.cronExpression = cronExpression;
    }

    @Override
    public void start() {
        scheduler.initialize();
        scheduler.schedule(() -> {
            if (isCompleted.compareAndSet(true, false)) {
                consumer.accept(this);
            }
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void onCompletion() {
        isCompleted.set(true);
    }

    @Override
    public void stop() {
        scheduler.shutdown();
    }

    @Override
    public Map<String, String> getInfo() {
        return ImmutableMap.<String, String>builder()
                .put("cronExpression", cronExpression)
                .build();
    }
}
