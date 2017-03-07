package com.betamedia.qe.af.webservice.business.runner;

import com.betamedia.qe.af.webservice.web.entities.RunTestParams;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    boolean isAssignable(RunTestParams params);

    void run(List<String> suitesFileNames);
}
