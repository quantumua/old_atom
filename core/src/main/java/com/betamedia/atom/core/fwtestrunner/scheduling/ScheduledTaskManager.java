package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by mbelyaev on 4/20/17.
 */
public interface ScheduledTaskManager {

    void createRepeatingTest(String name, String mailAddress, Properties properties, MultipartFile[] suites);

    void createScheduledTest(String name, String mailAddress, Properties properties, MultipartFile[] suites, String cronExpression);

    void stopTask(String name);

    Set<Map<String, String>> getInfo();

    void createScheduledTask(String name, String emailAddress, Properties properties, MultipartFile[] suites, String cronExpression);

    void createRepeatingTask(String name, String emailAddress, Properties properties, MultipartFile[] suites);

    List<TestTask> getTasks();

    void stopTask(UUID uuid);
}
