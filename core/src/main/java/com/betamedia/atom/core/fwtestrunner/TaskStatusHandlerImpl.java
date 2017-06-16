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
public class TaskStatusHandlerImpl implements TaskStatusHandler {
    private static final Logger logger = LogManager.getLogger(TaskStatusHandlerImpl.class);

    @Autowired
    private LoadingCache<UUID, TaskStatus> statusCache;

    @Override
    public TaskStatus get(UUID testId) {
        return statusCache.getUnchecked(testId);
    }

    @Override
    public void put(TaskStatus status) {
        statusCache.put(status.uuid, status);
    }
}
