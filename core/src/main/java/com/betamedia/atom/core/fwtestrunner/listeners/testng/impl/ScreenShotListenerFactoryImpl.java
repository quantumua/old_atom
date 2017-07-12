package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.fwtestrunner.listeners.testng.ScreenShotListenerFactory;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link ScreenShotListenerFactory} implementation for {@link ScreenShotListener} that is managed by Spring and
 *
 * @author mbelyaev
 * @since 5/29/17
 */
@Service
public class ScreenShotListenerFactoryImpl implements ScreenShotListenerFactory {
    @Autowired
    private StorageService storageService;

    @Override
    public ScreenShotListener get(String outputDirectory) {
        return new ScreenShotListener(outputDirectory, storageService, ScreenShotListener::getWebServiceURL);
    }
}
