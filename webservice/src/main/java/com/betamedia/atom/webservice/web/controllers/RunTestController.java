package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.betamedia.atom.core.fwtestrunner.TestTaskHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.betamedia.atom.core.utils.PropertiesUtils.getProperties;

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
    private TestTaskHandler testTaskHandler;

    @PostMapping("/upload/task")
    public List<TestTask> runTask(@RequestParam("properties") MultipartFile properties,
                                  @RequestParam("suites[]") MultipartFile[] suites,
                                  @RequestParam(value = "tempJar", required = false) MultipartFile tempJar) throws IOException {
        logger.info("Starting tests");
        return testRunnerHandler.handleTask(getProperties(properties), suites, tempJar, Collections.emptyList());
    }

    @PostMapping(value = "/status/{taskId}")
    public TestTask getTaskStatus(@PathVariable UUID taskId) {
        return testTaskHandler.get(taskId);
    }

}
