package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.scheduling.ContinuousTestManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.betamedia.atom.core.utils.PropertiesUtils.getProperties;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
@RestController
@RequestMapping
public class ScheduledTestController {
    private static final Logger logger = LogManager.getLogger(ScheduledTestController.class);
    @Autowired
    private ContinuousTestManagerImpl continuousTestManager;

    @PostMapping("/upload/test/scheduled")
    public TestInformation createScheduledTest(@RequestParam("name") String name,
                                               @RequestParam("properties") MultipartFile properties,
                                               @RequestParam("suites[]") MultipartFile[] suites,
                                               @RequestParam("cronExpression") Optional<String> cronExpression) throws IOException {
        logger.info("Scheduling test");
        return continuousTestManager.createTest(name, getProperties(properties), suites, cronExpression);
    }

    @GetMapping("/scheduled")
    public Set<TestInformation> getScheduledTests() {
        return continuousTestManager.getInfo();
    }

    @DeleteMapping("/scheduled/{testId}/stop")
    public void stopScheduledTest(@PathVariable UUID testId) {
        continuousTestManager.stopTest(testId);
    }


}
