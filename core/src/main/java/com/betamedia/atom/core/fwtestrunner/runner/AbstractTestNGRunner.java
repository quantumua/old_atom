package com.betamedia.atom.core.fwtestrunner.runner;

import com.betamedia.atom.core.fwtestrunner.RunnerResult;
import com.betamedia.atom.core.fwtestrunner.listeners.ConfigurableListenerFactory;
import com.betamedia.atom.core.fwtestrunner.listeners.impl.ScreenShotListener;
import com.betamedia.atom.core.fwtestrunner.storage.StorageException;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.TestNG;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractTestNGRunner implements TestRunner {
    private static final Logger logger = LogManager.getLogger(AbstractTestNGRunner.class);

    @Autowired
    private Collection<ConfigurableListenerFactory> listenerFactories;
    @Autowired
    private StorageService storageService;

    @Override
    public TestRunnerType getType() {
        return TestRunnerType.TESTNG;
    }

    @Override
    public final RunnerResult run(Properties properties, List<String> suites, String outputDirectory) {
        initializeEnvironment(properties);
        TestNG testng = new TestNG();
        testng.setOutputDirectory(outputDirectory);
        listenerFactories.stream()
                .map(f -> f.get(outputDirectory))
                .forEach(testng::addListener);
        testng.setTestSuites(suites);
        testng.run();
        return new RunnerResult(testng.hasFailure() || testng.hasSkip(),
                testng.getOutputDirectory() + "/emailable-report.html",
                getScreenshots(testng.getOutputDirectory())
        );
    }

    /**
     * Override to implement environment bootstrapping
     */
    public abstract void initializeEnvironment(Properties properties);

    private List<String> getScreenshots(String outputDirectory) {
        return getScreenshotStream(outputDirectory)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    private Stream<Path> getScreenshotStream(String outputDirectory) {
        try {
            return storageService.loadAll(outputDirectory, ScreenShotListener.SCREEN_SHOT_DIRECTORY);
        } catch (StorageException e) {
            logger.info("No screenshots found");
            return Stream.empty();
        }
    }

}
