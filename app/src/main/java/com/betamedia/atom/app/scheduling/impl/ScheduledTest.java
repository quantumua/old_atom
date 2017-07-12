package com.betamedia.atom.app.scheduling.impl;

import com.betamedia.atom.app.entity.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * Test that registers self-starting callback with {@link TaskScheduler} based on cron expression.
 * Stores resulting {@link ScheduledFuture} to prevent launching the same test multiple times.
 * Also manages the execution state in <code>allowedToStart</code> to prevent running new test executions on schedule
 * while the previous one has not been completed yet.
 *
 * @author mbelyaev
 * @since 6/16/17
 */
public class ScheduledTest extends ContinuousTest {
    private static final Logger logger = LogManager.getLogger(ScheduledTest.class);
    private final AtomicBoolean allowedToStart = new AtomicBoolean(true);
    private final TaskScheduler scheduler;
    private final String cronExpression;
    private volatile ScheduledFuture scheduledFuture;

    public ScheduledTest(Supplier<ListenableFuture<TestInformation>> testExecution, String cronExpression, TaskScheduler scheduler) {
        super(testExecution);
        this.scheduler = scheduler;
        this.cronExpression = cronExpression;
    }

    @Override
    public void start() {
        if (scheduledFuture != null) {
            logger.error("Attempted to start an already running scheduled test!", this);
            return;
        }
        scheduledFuture = scheduler.schedule(() -> {
            logger.info("Checking if execution is allowed.", this);
            if (allowedToStart.compareAndSet(true, false)) {
                logger.info("Running test execution.", this);
                runExecution().ifPresent(f -> f.addCallback(t -> this.onSuccess(), ex -> this.onSuccess()));
            }
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void stop() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
        logger.info("Gracefully terminating scheduler for the test.", this);
        if (scheduledFuture == null) {
            logger.error("Attempted to cancel an already stopped scheduled test!", this);
            return;
        }
        scheduledFuture.cancel(false);
    }

    @Override
    public boolean abort() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
        logger.info("Interrupting scheduler for the test.", this);
        if (scheduledFuture == null) {
            logger.error("Attempted to cancel a scheduled test that wasn't started!", this);
            return false;
        }
        return scheduledFuture.cancel(true);
    }

    private void onSuccess() {
        logger.info("Finished executing test iteration.", this);
        if (isEnabled.get()) {
            logger.info("Allowing next test iteration to start.", this);
            allowedToStart.set(true);
            return;
        }
        logger.info("The test will no longer be restarted.", this);
    }
}
