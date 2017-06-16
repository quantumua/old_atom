package com.betamedia.atom.core.fwtestrunner.scheduling;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author mbelyaev
 * @since 4/20/17
 */
public interface ContinuousTaskManager {
    void createScheduledTask(String name, String emailAddress, Properties properties, MultipartFile[] suites, String cronExpression);

    void createRepeatingTask(String name, String emailAddress, Properties properties, MultipartFile[] suites);

    void stopTask(String name);

    Set<Map<String, String>> getInfo();
}
