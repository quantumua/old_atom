package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.google.common.collect.ImmutableMap;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Created by mbelyaev on 4/19/17.
 */
public class ScheduledTestExecution<T> implements TestExecution, ExecutionListener<T> {

    private final AtomicBoolean isCompleted = new AtomicBoolean(true);
    private final Consumer<ExecutionListener<T>> consumer;
    private final String cronExpression;
    private final TaskScheduler scheduler;
    private ScheduledFuture scheduledFuture;


    public ScheduledTestExecution(Consumer<ExecutionListener<T>> consumer, String cronExpression, TaskScheduler scheduler) {
        this.consumer = consumer;
        this.cronExpression = cronExpression;
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        if (scheduledFuture != null) {
            return;
        }
        scheduledFuture = scheduler.schedule(() -> {
            if (isCompleted.compareAndSet(true, false)) {
                consumer.accept(this);
            }
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void onCompletion(T results) {
        isCompleted.set(true);
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(false);
    }

    @Override
    public Map<String, String> getInfo() {
        return ImmutableMap.<String, String>builder()
                .put("cronExpression", cronExpression)
                .build();
    }
}
