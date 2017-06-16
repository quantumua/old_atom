package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class ScheduledTask implements ContinuousTask {

    private final AtomicBoolean isCompleted = new AtomicBoolean(true);
    private final Supplier<List<TestTask>> taskExecution;
    private final String cronExpression;
    private final TaskScheduler scheduler;
    private ScheduledFuture scheduledFuture;
    private volatile List<TestTask> tasks;


    public ScheduledTask(Supplier<List<TestTask>> taskExecution, String cronExpression, TaskScheduler scheduler) {
        this.taskExecution = taskExecution;
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
                tasks = taskExecution.get();
//                reporting through TestTasks goes here
                isCompleted.set(true);
            }
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(false);
    }

    @Override
    public List<TestTask> getTasks() {
        return tasks.stream().map(t ->
                t.update()
                        .addProperty("cronExpression", cronExpression)
                        .build())
                .collect(Collectors.toList());
    }
}
