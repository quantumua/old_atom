package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.TestInformationHandler;
import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContinuousTestManagerImpl implements ContinuousTestManager {
    @Autowired
    private TestRunnerHandler handler;
    @Autowired
    private TestInformationHandler testInformationHandler;
    @Autowired
    private StorageService storageService;
    @Autowired
    private List<TestTaskCompletionListener> listeners;
    @Autowired
    private ContinuousTestFactory scheduledTaskFactory;
    @Autowired
    private ContinuousTestFactory repeatingTaskFactory;

    private ConcurrentMap<UUID, ContinuousTest> continuousTasks = new ConcurrentHashMap<>();

    @Override
    public TestInformation createTest(String name, Properties properties, MultipartFile[] suites, Optional<String> cronExpression) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Supplier<List<TestInformation>> testExecution = () -> handler.handleTest(properties, suitePaths, Optional.empty(), listeners);
        TestInformation testInformation = testInformationHandler.builder()
                .isContinuous(true)
                .withName(name)
                .withCronExpression(cronExpression.orElse(""))
                .withStatus(TestInformation.Status.CREATED)
                .build();
        ContinuousTest task = cronExpression.isPresent() ? scheduledTaskFactory.get(testInformation, testExecution) : repeatingTaskFactory.get(testInformation, testExecution);
        Optional.ofNullable(
                continuousTasks.put(testInformation.id, task))
                .ifPresent(ContinuousTest::stop);
        task.start();
        return testInformation;
    }

    @Override
    public void stopTest(UUID name) {
        Optional.ofNullable(continuousTasks.remove(name))
                .ifPresent(ContinuousTest::stop);
    }

    @Override
    public Set<TestInformation> getInfo() {
        return continuousTasks.entrySet()
                .stream()
                .map(e -> e.getValue().getTestInformation())
                .collect(Collectors.toSet());
    }

}
