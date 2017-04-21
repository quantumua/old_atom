package com.betamedia.qe.af.core.fwtestrunner.scheduling;

import java.util.Map;

/**
 * Created by mbelyaev on 4/19/17.
 */
public interface TestExecution {
    void start();

    void stop();

    Map<String, String> getInfo();
}
