package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class RepeatingTask implements ContinuousTask {

    private final AtomicBoolean isActive = new AtomicBoolean(true);
    private final Supplier<List<TestTask>> taskExecution;
    private final TaskExecutor executor;
    private volatile List<TestTask> tasks;

    public RepeatingTask(Supplier<List<TestTask>> taskExecution, TaskExecutor executor) {
        this.taskExecution = taskExecution;
        this.executor = executor;
    }

    @Override
    public void start() {
        executor.execute(() -> {
            tasks = taskExecution.get();
//          reporting through TestTasks goes here
            if (isActive.get()) {
                start();
            }
        });
    }

    @Override
    public void stop() {
        isActive.set(false);
    }

    @Override
    public List<TestTask> getTasks() {
        return tasks;
    }
}
