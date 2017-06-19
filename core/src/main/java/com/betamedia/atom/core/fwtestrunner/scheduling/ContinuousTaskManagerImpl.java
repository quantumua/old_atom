package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
@Component
public class ContinuousTaskManagerImpl implements ContinuousTaskManager {
    @Autowired
    private TestRunnerHandler handler;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private TaskExecutor asyncTaskExecutor;
    @Autowired
    private StorageService storageService;
    @Autowired
    private List<TestTaskCompletionListener> listeners;

    private ConcurrentMap<String, ContinuousTask> continuousTasks = new ConcurrentHashMap<>();

    @Override
    public void createTask(String name, String emailAddress, Properties properties, MultipartFile[] suites, Optional<String> cronExpression) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Supplier<List<TestTask>> runnable = () -> handler.handleTask(properties, suitePaths, Optional.empty(), listeners);
        ContinuousTask task = getTask(runnable, cronExpression);
        Optional.ofNullable(
                continuousTasks.put(name, task))
                .ifPresent(ContinuousTask::stop);
        task.start();
    }

    @Override
    public void stopTask(String name) {
        Optional.ofNullable(continuousTasks.remove(name))
                .ifPresent(ContinuousTask::stop);
    }

    @Override
    public Set<TestTask> getInfo() {
        return continuousTasks.entrySet()
                .stream()
                .map(e -> e.getValue().getTask())
                .collect(Collectors.toSet());
    }

    private ContinuousTask getTask(Supplier<List<TestTask>> runnable, Optional<String> cronExpression) {
        return cronExpression
                .map((Function<String, ContinuousTask>) expr -> new ScheduledTask(runnable, expr, taskScheduler))
                .orElseGet(() -> new RepeatingTask(runnable, asyncTaskExecutor));
    }


}
