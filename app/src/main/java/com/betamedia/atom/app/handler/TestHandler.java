package com.betamedia.atom.app.handler;

import com.betamedia.atom.app.entity.TestInformation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestHandler {

    String TEST_OUTPUT_DIRECTORY = "test-output/";

    List<TestInformation> handleTest(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar);

    List<TestInformation> handleTest(Properties properties, List<String> suitePaths, Optional<String> tempJarPath);

    boolean abort(UUID uuid);
}
