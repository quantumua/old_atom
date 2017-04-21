package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.fwtestrunner.scheduling.ExecutionListener;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageService;
import com.betamedia.qe.af.webservice.web.entities.RunTestParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private StorageService storageService;

    @GetMapping("/run")
    public List<String> run(@Valid RunTestParams params) throws IOException {
        return testRunnerHandler.handle(getProperties(params.getSut()), params.getSuite(), null, ExecutionListener.nullListener());
    }

    @PostMapping("/upload/suite")
    public List<String> handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                         @RequestParam("suites[]") MultipartFile[] suites,
                                         @RequestParam(value = "dataSources[]", required = false) MultipartFile[] dataSources,
                                         @RequestParam(value = "tempJar", required = false) MultipartFile tempJar) throws IOException {
        logger.info("Starting tests");
        return testRunnerHandler.handle(getProperties(properties), storageService.store(suites), tempJar, ExecutionListener.nullListener());
    }

    @PostMapping(value = "/exists")
    public Boolean getReportStatus(@RequestBody String path) {
        return Paths.get("test-output/" + path).toFile().exists();
    }

}
