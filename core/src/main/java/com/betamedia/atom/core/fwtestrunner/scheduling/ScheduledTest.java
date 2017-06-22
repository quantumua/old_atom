package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class ScheduledTest extends ContinuousTest {
    private static final Logger logger = LogManager.getLogger(RepeatingTest.class);
    private final AtomicBoolean allowedToStart = new AtomicBoolean(true);
    private final TaskScheduler scheduler;
    private volatile ScheduledFuture scheduledFuture;

    public ScheduledTest(TestInformation testInformation, Function<Consumer<List<TestInformation>>, List<TestInformation>> testExecution, Consumer<TestInformation> onTestSubmitCompletion, TaskScheduler scheduler) {
        super(testInformation, testExecution, onTestSubmitCompletion);
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        if (scheduledFuture != null) {
            logger.error("Attempted to start an already started continuous test!", this);
            return;
        }
        scheduledFuture = scheduler.schedule(() -> {
            logger.info("Checking if execution is allowed.", this);
            if (allowedToStart.compareAndSet(true, false)) {
                logger.info("Running test execution.", this);
                runExecution();
            }
        }, new CronTrigger(testInformation.cronExpression));
    }

    @Override
    public void stop() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
        logger.info("Gracefully terminating scheduler for the test.", this);
        scheduledFuture.cancel(false);
    }

    @Override
    protected void onIterationCompletion(List<TestInformation> iterationResults) {
        logger.info("Finished executing test iteration.", this);
        if (isEnabled.get()) {
            logger.info("Allowing next test iteration to start.", this);
            allowedToStart.set(true);
            return;
        }
        logger.info("The test will no longer be restarted.", this);
        testInformation = testInformation.update().withStatus(TestInformation.Status.COMPLETED).build();
        onTestSubmitCompletion.accept(this.testInformation);
    }
}
