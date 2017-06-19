package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

/**
 * @author mbelyaev
 * @since 4/20/17
 */
public interface ContinuousTaskManager {
    void createTask(String name, String emailAddress, Properties properties, MultipartFile[] suites, Optional<String> cronExpression);

    void stopTask(String name);

    Set<TestTask> getInfo();
}
