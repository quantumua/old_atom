package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.scheduling.impl.ContinuousTestHandlerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
    private ContinuousTestHandlerImpl continuousTestManager;

    @PostMapping("/upload/test/scheduled")
    public List<TestInformation> createScheduledTest(@RequestParam("name") String name,
                                                     @RequestParam("properties") MultipartFile properties,
                                                     @RequestParam("emailAddress") String emailAddress,
                                                     @RequestParam("suites[]") MultipartFile[] suites,
                                                     @RequestParam("cronExpression") Optional<String> cronExpression) throws IOException {
        logger.info("Scheduling test");
        return continuousTestManager.handleTest(name, getProperties(properties), suites, cronExpression, emailAddress);
    }

    @GetMapping("/scheduled")
    public Set<TestInformation> getScheduledTests() {
        return continuousTestManager.getInfo();
    }

    @DeleteMapping("/scheduled/{testId}/stop")
    public void stopScheduledTest(@PathVariable UUID testId) {
        continuousTestManager.stop(testId);
    }

    @DeleteMapping(value = "/scheduled/{testId}/abort")
    public boolean abortScheduledTest(@PathVariable UUID testId) {
        return continuousTestManager.abort(testId);
    }

}
