package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.TestInformationHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class ScheduledTest extends ContinuousTest {
    private final TaskScheduler scheduler;
    private volatile ScheduledFuture scheduledFuture;

    public ScheduledTest(TestInformation taskEntry, Supplier<List<TestInformation>> taskExecution, TaskScheduler scheduler) {
        super(taskEntry, taskExecution);
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
        }, new CronTrigger(testInformation.cronExpression));
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(false);
    }

}
