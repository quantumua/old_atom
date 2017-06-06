package com.betamedia.atom.core.fwtestrunner.scheduling;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by mbelyaev on 4/20/17.
 */
public interface TestExecutionManager {

    void createRepeatingTest(String name, String mailAddress, Properties properties, List<String> suites);

    void createScheduledTest(String name, String mailAddress, Properties properties, List<String> suites, String cronExpression);

    void stopTask(String name);

    Set<Map<String, String>> getInfo();
}
