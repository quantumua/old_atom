package com.betamedia.atom.app.scheduling.impl;

import com.betamedia.atom.app.entity.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * Base class for self-managing continuous tests. Test execution is contained in {@link Supplier} of {@link ListenableFuture}
 * result of {@link org.springframework.core.task.AsyncListenableTaskExecutor} invocation. Expected to apply callbacks
 * to {@link ListenableFuture} to manage own lifecycle.<br/>
 * <code>isEnabled</code> flag is checked before each test iteration and
 * is used to shut down test execution.
 *
 * @author mbelyaev
 * @since 4/19/17
 */
public abstract class ContinuousTest {
    private static final Logger logger = LogManager.getLogger(ContinuousTest.class);
    protected final AtomicBoolean isEnabled = new AtomicBoolean(true);
    protected final Supplier<ListenableFuture<TestInformation>> testExecution;

    protected ContinuousTest(Supplier<ListenableFuture<TestInformation>> testExecution) {
        this.testExecution = testExecution;
    }

    protected Optional<ListenableFuture<TestInformation>> runExecution() {
        if (!isEnabled.get()) {
            logger.error("Attempting to start a disabled continuous test!", this);
            return Optional.empty();
        }
        return Optional.of(testExecution.get());
    }

    public abstract void start();

    public abstract void stop();

    abstract boolean abort();

}
