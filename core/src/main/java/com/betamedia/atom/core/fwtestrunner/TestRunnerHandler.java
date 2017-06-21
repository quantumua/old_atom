package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.listeners.TestCompletionListener;
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

    List<TestInformation> handleTest(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar, List<TestCompletionListener> listeners);

    List<TestInformation> handleTest(Properties properties, List<String> suitePaths, Optional<String> tempJarPath, List<TestCompletionListener> listeners);

}
