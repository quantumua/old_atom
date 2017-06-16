package com.betamedia.atom.core.fwtestrunner;

import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
@Service
public class TestTaskHandlerImpl implements TestTaskHandler {
    private static final Logger logger = LogManager.getLogger(TestTaskHandlerImpl.class);

    @Autowired
    private LoadingCache<UUID, TestTask> taskCache;

    @Override
    public TestTask get(UUID testId) {
        return taskCache.getUnchecked(testId);
    }

    @Override
    public void put(TestTask task) {
        taskCache.put(task.uuid, task);
    }
}
