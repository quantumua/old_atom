package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.core.fwtestrunner.TestTask;
import com.betamedia.atom.core.fwtestrunner.TestTaskHandler;
import com.betamedia.atom.core.fwtestrunner.scheduling.ScheduledTaskManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.betamedia.atom.core.utils.PropertiesUtils.getProperties;

/**
 * Created by mbelyaev on 4/19/17.
 */
@RestController
@RequestMapping
public class ScheduledTestController {
    private static final Logger logger = LogManager.getLogger(ScheduledTestController.class);
    @Autowired
    private ScheduledTaskManager scheduledTaskManager;
    @Autowired
    private TestTaskHandler testTaskHandler;

    @PostMapping("/upload/suite/scheduled")
    public void createScheduledTest(@RequestParam("name") String name,
                                    @RequestParam("emailAddress") String emailAddress,
                                    @RequestParam("properties") MultipartFile properties,
                                    @RequestParam("suites[]") MultipartFile[] suites,
                                    @RequestParam("cronExpression") String cronExpression) throws IOException {
        logger.info("Scheduling test");
        scheduledTaskManager.createScheduledTest(name, emailAddress, getProperties(properties), suites, cronExpression);
    }

    @PostMapping("/upload/suite/repeating")
    public void createRepeatingTest(@RequestParam("name") String name,
                                    @RequestParam("emailAddress") String emailAddress,
                                    @RequestParam("properties") MultipartFile properties,
                                    @RequestParam("suites[]") MultipartFile[] suites) throws IOException {
        logger.info("Scheduling test");
        scheduledTaskManager.createRepeatingTest(name, emailAddress, getProperties(properties), suites);
    }

    @GetMapping("/scheduled")
    public Set<Map<String, String>> getScheduledTestsInfo() {
        return scheduledTaskManager.getInfo();
    }

    @DeleteMapping("/scheduled/{name}/stop")
    public void stopScheduledTest(@PathVariable String name) {
        scheduledTaskManager.stopTask(name);
    }

    @PostMapping("/upload/task/scheduled")
    public void createScheduledTask(@RequestParam("name") String name,
                                    @RequestParam("emailAddress") String emailAddress,
                                    @RequestParam("properties") MultipartFile properties,
                                    @RequestParam("suites[]") MultipartFile[] suites,
                                    @RequestParam("cronExpression") String cronExpression) throws IOException {
        logger.info("Scheduling test");
        scheduledTaskManager.createScheduledTask(name, emailAddress, getProperties(properties), suites, cronExpression);
    }

    @PostMapping("/upload/task/repeating")
    public void createRepeatingTask(@RequestParam("name") String name,
                                    @RequestParam("emailAddress") String emailAddress,
                                    @RequestParam("properties") MultipartFile properties,
                                    @RequestParam("suites[]") MultipartFile[] suites) throws IOException {
        logger.info("Scheduling test");
        scheduledTaskManager.createRepeatingTask(name, emailAddress, getProperties(properties), suites);
    }

    @GetMapping("/tasks/scheduled")
    public List<TestTask> getTasks() {
        return scheduledTaskManager.getTasks();
    }

    @DeleteMapping("/tasks/scheduled/{uuid}")
    public void stopTask(@PathVariable UUID uuid) {
        scheduledTaskManager.stopTask(uuid);
    }

}
