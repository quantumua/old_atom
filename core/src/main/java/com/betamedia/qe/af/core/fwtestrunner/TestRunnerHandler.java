package com.betamedia.qe.af.core.fwtestrunner;

import com.betamedia.qe.af.core.fwtestrunner.scheduling.ExecutionListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunnerHandler {

    String TEST_OUTPUT_DIRECTORY = "test-output/";

    List<String> handle(Properties properties, List<String> suites, MultipartFile tempJar, ExecutionListener<List<RunnerResult>> listener);
}
