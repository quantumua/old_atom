package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
public abstract class ContinuousTest {
    protected final AtomicBoolean isEnabled = new AtomicBoolean(true);
    protected final Supplier<List<TestInformation>> testExecution;
    protected TestInformation testInformation;

    protected ContinuousTest(TestInformation testInformation, Supplier<List<TestInformation>> testExecution) {
        this.testExecution = testExecution;
        this.testInformation = testInformation;
    }

    public TestInformation getTestInformation() {
        return testInformation;
    }

    abstract void start();

    abstract void stop();

    protected void runExecution() {
        testInformation = testInformation.update()
                .withChildTasks(
                        Stream.concat(
                                testInformation.childTaskIds.stream(),
                                testExecution.get().stream().map(t -> t.id))
                                .collect(Collectors.toList())).build();
    }

    protected void reportCompletion() {
        testInformation = testInformation.update().withStatus(TestInformation.Status.COMPLETED).build();
    }

}
