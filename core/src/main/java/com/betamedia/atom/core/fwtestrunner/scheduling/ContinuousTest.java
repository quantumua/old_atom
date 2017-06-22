package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
public abstract class ContinuousTest {
    private static final Logger logger = LogManager.getLogger(ContinuousTest.class);
    protected final AtomicBoolean isEnabled = new AtomicBoolean(true);
    protected final Function<Consumer<List<TestInformation>>, List<TestInformation>> testExecution;
    protected TestInformation testInformation;
    protected Consumer<TestInformation> onTestSubmitCompletion;

    protected ContinuousTest(TestInformation testInformation, Function<Consumer<List<TestInformation>>, List<TestInformation>> testExecution, Consumer<TestInformation> onTestSubmitCompletion) {
        this.testExecution = testExecution;
        this.testInformation = testInformation;
        this.onTestSubmitCompletion = onTestSubmitCompletion;
    }

    public TestInformation getTestInformation() {
        return testInformation;
    }

    protected void runExecution() {
        if (!isEnabled.get()) {
            logger.error("Attempted to start a disabled continuous test!", this);
            return;
        }
        if (testInformation.status.equals(TestInformation.Status.CREATED)) {
            logger.info("Starting continuous test for the first time.", this);
            testInformation = testInformation.update().withStatus(TestInformation.Status.RUNNING).build();
        }
        testInformation = testInformation.update()
                .withChildTasks(
                        Stream.concat(
                                testInformation.childTaskIds.stream(),
                                testExecution.apply(this::onIterationCompletion)
                                        .stream()
                                        .map(t -> t.id))
                                .collect(Collectors.toList())).build();
        onTestSubmitCompletion.accept(testInformation);
    }

    abstract void start();

    abstract void stop();

    abstract void onIterationCompletion(List<TestInformation> iterationResults);

}
