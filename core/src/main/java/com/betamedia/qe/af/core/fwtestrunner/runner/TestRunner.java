package com.betamedia.qe.af.core.fwtestrunner.runner;

import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    TestRunnerType getType();

    void run(Properties properties, List<String> suites, String outputDirectory);
}
