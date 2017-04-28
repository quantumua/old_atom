package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.webservice.holders.DependenciesInfoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getCoreVersion() {
        return new ResponseEntity<>(dependenciesInfoHolder.getCoreVersion(), HttpStatus.OK);
    }

    @GetMapping("/version/testslibrary")
    public ResponseEntity<String> getTestsLibraryVersion() {
        contextClassLoaderManagingExecutor.getJarVersion();
        return new ResponseEntity<>(contextClassLoaderManagingExecutor.getJarVersion(), HttpStatus.OK);
    }
}
