package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.webservice.business.ClassLoaderInvocationHandler;
import com.betamedia.qe.af.webservice.business.RunTestHandler;
import com.betamedia.qe.af.webservice.storage.StorageFileNotFoundException;
import com.betamedia.qe.af.webservice.storage.StorageService;
import com.betamedia.qe.af.webservice.web.entities.RunTestParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.core.utils.PropertiesUtils.getProperties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@RestController
@RequestMapping
public class RunTestController {

    private static final Logger logger = LogManager.getLogger(RunTestController.class);

    @Autowired
    private RunTestHandler runTestHandler;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ClassLoaderInvocationHandler classLoaderInvocationHandler;

    @GetMapping("/run")
    public List<String> run(@Valid RunTestParams params) throws IOException {
        return runTestHandler.handle(getProperties(params.getSut()), params.getSuite());
    }

    //TODO better endpoint scheme
    @PostMapping("/upload/tests")
    public ResponseEntity<String> uploadTestJar(MultipartFile jar) throws MalformedURLException {
        if (jar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String jarPath = storageService.store(jar);
        classLoaderInvocationHandler.setClassLoader(
                new URLClassLoader(
                        new URL[]{Paths.get(jarPath).toUri().toURL()},
                        Thread.currentThread().getContextClassLoader()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/upload/suite")
    public List<String> handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                         @RequestParam("suites[]") MultipartFile[] suites,
                                         @RequestParam("dataSources[]") MultipartFile[] dataSources,
                                         RedirectAttributes redirectAttributes) throws IOException {
        logger.info("Starting tests");
        List<String> suitePaths = Arrays.stream(suites)
                .map(storageService::store)
                .collect(Collectors.toList());

        return runTestHandler.handle(getProperties(properties), suitePaths);
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
