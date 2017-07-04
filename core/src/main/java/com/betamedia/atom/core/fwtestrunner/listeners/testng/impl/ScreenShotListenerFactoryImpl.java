package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.fwtestrunner.listeners.testng.ConfigurableListenerFactory;
import com.betamedia.atom.core.fwtestrunner.runner.TestNGRunner;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link ConfigurableListenerFactory} implementation for {@link ScreenShotListener} that is managed by Spring and
 * injected into {@link TestNGRunner}
 *
 * @author mbelyaev
 * @since 5/29/17
 * @see TestNGRunner
 */
@Service
public class ScreenShotListenerFactoryImpl implements ConfigurableListenerFactory<ScreenShotListener> {
    @Autowired
    private StorageService storageService;

    @Override
    public ScreenShotListener get(String outputDirectory) {
        return new ScreenShotListener(outputDirectory, storageService);
    }
}
