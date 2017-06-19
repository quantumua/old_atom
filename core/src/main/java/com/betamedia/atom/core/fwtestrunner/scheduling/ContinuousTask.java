package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
public abstract class ContinuousTask {
    protected final AtomicBoolean isEnabled = new AtomicBoolean(true);
    protected final Supplier<List<TestTask>> taskExecution;
    protected TestTask taskEntry;

    protected ContinuousTask(Supplier<List<TestTask>> taskExecution) {
        this.taskExecution = taskExecution;
        this.taskEntry = TestTask.builder()
                        .isContinuous(true)
                        .withStatus(TestTask.Status.CREATED)
                        .build();
    }

    abstract void start();

    abstract void stop();

    public TestTask getTask() {
        return taskEntry;
    }

    protected void runExecution(){
        taskEntry = taskEntry.update()
                .withChildTasks(
                        Stream.concat(
                                taskEntry.childTaskIds.stream(),
                                taskExecution.get().stream().map(t -> t.uuid))
                                .collect(Collectors.toList()))
                .build();
    }

    protected void reportCompletion(){
        taskEntry = taskEntry.update().withStatus(TestTask.Status.COMPLETED).build();
    }
}
