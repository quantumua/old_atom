package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunnerHandler {

    String TEST_OUTPUT_DIRECTORY = "test-output/";

    List<TestTask> handleTask(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar, List<TestTaskCompletionListener> listeners);

    List<TestTask> handleTask(Properties properties, List<String> suitePaths, Optional<String> tempJarPath, List<TestTaskCompletionListener> listeners);

}
