package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.app.classloader.ContextClassLoaderManagingExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mbelyaev on 4/21/17.
 */
@RestController
@RequestMapping
public class TestLibraryController {
    @Autowired
    private ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor;

    @PostMapping("/upload/library")
    public ResponseEntity<String> uploadTestJar(MultipartFile jar) {
        if (jar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        contextClassLoaderManagingExecutor.store(jar);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
