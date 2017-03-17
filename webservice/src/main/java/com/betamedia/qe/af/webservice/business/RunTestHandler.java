package com.betamedia.qe.af.webservice.business;

import java.util.List;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface RunTestHandler {

    List<String> handle(Properties properties, List<String> suitesFileNames);
}
