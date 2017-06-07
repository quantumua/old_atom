package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.scheduling.ExecutionListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunnerHandler {

    String TEST_OUTPUT_DIRECTORY = "test-output/";

    List<ExecutionArguments> handle(Properties properties, MultipartFile[] suites, MultipartFile tempJar, ExecutionListener<List<RunnerResult>> listener);

    List<ExecutionArguments> handle(Properties properties, List<String> suitePaths, String tempJarPath, ExecutionListener<List<RunnerResult>> listener);
}
