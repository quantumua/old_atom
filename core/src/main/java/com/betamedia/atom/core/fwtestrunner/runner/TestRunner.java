package com.betamedia.atom.core.fwtestrunner.runner;

import com.betamedia.atom.core.fwtestrunner.RunnerResult;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    TestRunnerType getType();

    RunnerResult run(Properties properties, List<String> suites, String outputDirectory);
}
