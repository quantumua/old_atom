package com.betamedia.qe.af.business.runner;

import com.betamedia.qe.af.entities.web.RunTestParams;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    boolean isAssignable(RunTestParams params);

    void run(List<String> suitesFileNames);
}
