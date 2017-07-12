package com.betamedia.atom.app.handler;

import com.betamedia.atom.app.entity.TestInformation;
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
public class TestInformationHandlerImpl implements TestInformationHandler {
    private static final Logger logger = LogManager.getLogger(TestInformationHandler.class);

    @Autowired
    private LoadingCache<UUID, TestInformation> testInformationCache;

    @Override
    public TestInformation get(UUID testId) {
        return testInformationCache.getIfPresent(testId);
    }

    @Override
    public void put(TestInformation task) {
        testInformationCache.put(task.id, task);
    }

}
