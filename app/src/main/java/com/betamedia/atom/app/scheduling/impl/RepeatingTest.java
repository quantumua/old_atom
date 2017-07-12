package com.betamedia.atom.app.scheduling.impl;

import com.betamedia.atom.app.entity.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.function.Supplier;


/**
 * Test that restarts itself on completion by applying self-invoking callback to test result.
 *
 * @author mbelyaev
 * @since 6/16/17
 */
public class RepeatingTest extends ContinuousTest {
    private static final Logger logger = LogManager.getLogger(RepeatingTest.class);
    private Optional<ListenableFuture<TestInformation>> future = Optional.empty();

    public RepeatingTest(Supplier<ListenableFuture<TestInformation>> testExecution) {
        super(testExecution);
    }

    @Override
    public void start() {
        future = runExecution();
        future.ifPresent(f -> f.addCallback(t -> this.onSuccess(), ex -> this.onSuccess()));
    }

    @Override
    public void stop() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
    }

    @Override
    public boolean abort() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
        logger.info("Forcefully interrupting test.", this);
        return future.map(f -> f.cancel(true)).orElse(false);
    }

    private void onSuccess() {
        logger.info("Finished executing test iteration.", this);
        if (isEnabled.get()) {
            logger.info("Restarting continuous test.", this);
            start();
            logger.info("Continuous test restarted.", this);
            return;
        }
        logger.info("The test will no longer be restarted.", this);
    }

}
