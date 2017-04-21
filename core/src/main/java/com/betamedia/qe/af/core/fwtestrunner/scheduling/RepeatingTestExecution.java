package com.betamedia.qe.af.core.fwtestrunner.scheduling;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


/**
 * Created by mbelyaev on 4/19/17.
 */
public class RepeatingTestExecution implements TestExecution, ExecutionListener {

    private final Consumer<ExecutionListener> consumer;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    public RepeatingTestExecution(Consumer<ExecutionListener> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void start() {
        executor.submit(() -> consumer.accept(this));
    }

    @Override
    public void stop() {
        executor.shutdown();
    }

    @Override
    public void onCompletion() {
        start();
    }

    @Override
    public Map<String, String> getInfo() {
        return Collections.emptyMap();
    }

}
