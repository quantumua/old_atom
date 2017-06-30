package com.betamedia.atom.core.testlink;

import com.betamedia.atom.core.testlink.persistence.entities.TestLinkTestCase;
import com.betamedia.atom.core.testlink.persistence.repositories.TestCaseVersionsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import testlink.api.java.client.TestLinkAPIClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Service to provide TestLink integration.
 * It is used to update test result status in conjunction with {@link TestLinkListener}.
 *
 * Created by Oleksandr Losiev on 5/16/17.
 */

@Service
@PropertySource("classpath:testlink.properties")
public class TestLinkService {

    @Value("${testlink.testPlanId}")
    private int testPlanId;
    @Value("${testlink.buildId}")
    private int buildId;
    @Value("${testlink.url}")
    private String url;
    @Value("${testlink.key}")
    private String devKey;

    private TestLinkAPIClient api;
    private static final Logger log = LogManager.getLogger(TestLinkService.class);
    private static final Long automationExecutionType = 2l;

    @Autowired
    public TestCaseVersionsRepository testCaseRepository;

    @PostConstruct
    public void init() {
        api = new TestLinkAPIClient(devKey, url);
    }

    void updateTestCase(Optional<String> testCaseDisplayId, TestCaseResultStatus testCaseResultStatus) {
        if (!testCaseDisplayId.isPresent()) {
            log.info("Test case display id was not provided for this test.");
            return;
        }

        int testCaseId;
        try {
            testCaseId = getTestCaseIdByNumericalDisplayId(splitDisplayId(testCaseDisplayId));
        } catch (IllegalArgumentException e) {
            log.warn("Test case id was not found for a given display id: " + testCaseDisplayId);
            return;
        }
/*
        try {
            String testResultStatus = testCaseResultStatus.getStatus();
            TestLinkAPIResults testLinkAPIResults = api.reportTestCaseResult(testPlanId, testCaseId, buildId, "", testResultStatus);
            log.info("Test Case results update status : " + testLinkAPIResults);
        } catch (TestLinkAPIException e) {
            log.error("An exception has occurred while updating the test case: ", e);
        }
*/
    }

    private long splitDisplayId(Optional<String> testCaseDisplayId) {
        String[] splitId = testCaseDisplayId.get().split("-");
        return Long.valueOf(splitId[splitId.length-1]);
    }

    private int getTestCaseIdByNumericalDisplayId(Long displayId) throws IllegalArgumentException {
        List<TestLinkTestCase> testCases = testCaseRepository.findByExternalIdAndExecutionType(displayId, automationExecutionType);
        if (testCases.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return testCases.get(0).getId();
    }

}

