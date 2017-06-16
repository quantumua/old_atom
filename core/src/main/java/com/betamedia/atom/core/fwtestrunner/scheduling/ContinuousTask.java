package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestTask;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
public interface ContinuousTask {
    void start();

    void stop();

    default Map<String, String> getInfo() {
        return Collections.emptyMap();
    }

    default List<TestTask> getTasks() {
        return Collections.emptyList();
    }

}
