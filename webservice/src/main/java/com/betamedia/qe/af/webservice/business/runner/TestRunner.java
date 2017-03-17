package com.betamedia.qe.af.webservice.business.runner;

import com.betamedia.qe.af.webservice.business.types.TestType;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    TestType getType();

    List<String> run(Properties properties, List<String> suitesFileNames);
}
