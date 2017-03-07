package com.betamedia.qe.af.webservice.business;

import com.betamedia.qe.af.webservice.web.entities.RunTestParams;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface RunTestHandler {
    void handle(RunTestParams params);
}
