package com.betamedia.atom.core.fwtestrunner.runner;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    TestRunnerType getType();

    TestTask run(TestTask task);
}
