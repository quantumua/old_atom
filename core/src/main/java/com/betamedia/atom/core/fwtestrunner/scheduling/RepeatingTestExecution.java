package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;


/**
 * Created by mbelyaev on 4/19/17.
 */
public class RepeatingTestExecution<T> implements ContinuousTask, ExecutionListener<T> {

    private final AtomicBoolean isActive = new AtomicBoolean(true);
    private final Consumer<ExecutionListener<T>> consumer;
    private final TaskExecutor executor;

    public RepeatingTestExecution(Consumer<ExecutionListener<T>> consumer, TaskExecutor executor) {
        this.consumer = consumer;
        this.executor = executor;
    }

    @Override
    public void start() {
        executor.execute(() -> consumer.accept(this));
    }

    @Override
    public void stop() {
        isActive.set(false);
    }

    @Override
    public void onCompletion(T results) {
        if (isActive.get()) {
            start();
        }
    }

    @Override
    public Map<String, String> getInfo() {
        return Collections.emptyMap();
    }


}
