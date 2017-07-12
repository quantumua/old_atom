package com.betamedia.atom.app.runner;

import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.types.TestRunnerType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    TestRunnerType getType();

    TestInformation run(TestInformation task);
}
