package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class ScheduledTask extends ContinuousTask {
    private final String cronExpression;
    private final TaskScheduler scheduler;
    private volatile ScheduledFuture scheduledFuture;

    public ScheduledTask(Supplier<List<TestTask>> taskExecution, String cronExpression, TaskScheduler scheduler) {
        super(taskExecution);
        this.cronExpression = cronExpression;
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        if (scheduledFuture != null) {
            return;
        }
        scheduledFuture = scheduler.schedule(() -> {
            if (isEnabled.compareAndSet(true, false)) {
                runExecution();
                isEnabled.set(true);
                return;
            }
            reportCompletion();
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(false);
    }

}
