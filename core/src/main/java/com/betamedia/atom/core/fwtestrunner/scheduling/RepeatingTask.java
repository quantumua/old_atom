package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class RepeatingTask extends ContinuousTask {
    private final TaskExecutor executor;

    public RepeatingTask(Supplier<List<TestTask>> taskExecution, TaskExecutor executor) {
        super(taskExecution);
        this.executor = executor;
    }

    @Override
    public void start() {
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
