package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageFileNotFoundException;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageService;
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
    private TestRunnerHandler testRunnerHandler;
    @Autowired
    private ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor;
    @Autowired
    private StorageService storageService;
    
    @GetMapping("/run")
    public List<String> run(@Valid RunTestParams params) throws IOException {
        return testRunnerHandler.handle(getProperties(params.getSut()), params.getSuite(), null);
    }

    @PostMapping("/upload/tests")
    public ResponseEntity<String> uploadTestJar(MultipartFile jar) throws MalformedURLException {
        if (jar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        contextClassLoaderManagingExecutor.store(jar);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/upload/suite")
    public List<String> handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                         @RequestParam("suites[]") MultipartFile[] suites,
                                         @RequestParam(value = "dataSources[]", required = false) MultipartFile[] dataSources,
                                         @RequestParam(value = "tempJar", required = false) MultipartFile tempJar,
                                         RedirectAttributes redirectAttributes) throws IOException {
        logger.info("Starting tests");
        List<String> suitePaths = Arrays.stream(suites)
                .map(storageService::store)
                .collect(Collectors.toList());

        return testRunnerHandler.handle(getProperties(properties), suitePaths, tempJar);
    }

    @PostMapping(value = "/exists")
    public Boolean getReportStatus(@RequestBody String path) {
        return Paths.get("test-output/" + path).toFile().exists();
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
