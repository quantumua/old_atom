package com.betamedia.atom.app.runner.impl;

import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.runner.TestRunner;
import com.betamedia.atom.app.types.TestRunnerType;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.ScreenShotListener;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Configures and launches {@link TestNG} instance according to provided parameters and reports the execution status.
 *
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunner implements TestRunner {
    private static final Logger logger = LogManager.getLogger(TestNGRunner.class);

    @Override
    public TestRunnerType getType() {
        return TestRunnerType.TESTNG;
    }

    @Override
    public TestInformation run(TestInformation task) {
        TestNG testng = new TestNG();
        testng.setOutputDirectory(task.reportDirectory);
        testng.setTestSuites(task.suites);
        TestInformation startedTask = task.update()
                .withStatus(TestInformation.Status.RUNNING)
                .withReportURL(task.reportDirectory + "/index.html")
                .withEmailReportURL(task.reportDirectory + "/emailable-report.html")
                .build();
        try {
            testng.run();
        } catch (Exception e) {
            logger.error("TestNG exception!", e);
            return startedTask.update()
                    .withStatus(TestInformation.Status.FAILED)
                    .hasFailed(true)
                    .build();
        }
        return startedTask.update()
                .withStatus(TestInformation.Status.COMPLETED)
                .hasFailed(testng.hasFailure() || testng.hasSkip())
                .withAttachmentURLs(getScreenshots(task.reportDirectory))
                .build();
    }

    private List<String> getScreenshots(String outputDirectory) {
        return getScreenshotStream(outputDirectory)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    private Stream<Path> getScreenshotStream(String outputDirectory) {
        try {
            return FileSystemStorageService.loadAll(outputDirectory, ScreenShotListener.SCREEN_SHOT_DIRECTORY);
        } catch (StorageException e) {
            logger.info("No screenshots found");
            return Stream.empty();
        }
    }

}
