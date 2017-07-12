package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.app.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.webservice.holders.DependenciesInfoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/28/17.
 */
@RestController
public class VersionController {
    @Autowired
    private ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor;
    @Autowired
    private DependenciesInfoHolder dependenciesInfoHolder;

    @GetMapping("/version/core")
    public Map<String, String> getCoreVersion() {
        Map<String, String> response = new HashMap<>();
        response.put("core.version", dependenciesInfoHolder.getCoreVersion());
        return response;
    }

    @GetMapping("/version/testslibrary")
    public Map<String, String> getTestsLibraryVersion() {
        Map<String, String> response = new HashMap<>();
        response.put("testslibrary.version", contextClassLoaderManagingExecutor.getJarVersion());
        return response;

    }
}
