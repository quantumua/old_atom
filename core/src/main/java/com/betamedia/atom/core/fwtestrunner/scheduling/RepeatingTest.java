package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.TestInformationHandler;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.function.Supplier;


/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class RepeatingTest extends ContinuousTest {
    private final TaskExecutor executor;

    public RepeatingTest(TestInformation taskEntry, Supplier<List<TestInformation>> taskExecution, TaskExecutor executor) {
        super(taskEntry, taskExecution);
        this.executor = executor;
    }

    @Override
    public void start() {
        if (testInformation.status.equals(TestInformation.Status.CREATED))
            testInformation = testInformation.update().withStatus(TestInformation.Status.RUNNING).build();
        executor.execute(() -> {
            runExecution();
            if (isEnabled.get()) {
                start();
                return;
            }
            reportCompletion();
        });
    }

    @Override
    public void stop() {
        isEnabled.set(false);
    }

}
