package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
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

    private TestLinkAPI api;
    private static final Logger log = LogManager.getLogger(TestLinkService.class);

    @PostConstruct
    public void init() {
        try {
            api = new TestLinkAPI(new URL(url), devKey);
        } catch (MalformedURLException e) {
            log.error("Cant init TestLink api due to exception ", e);
        }
    }

    void updateTestCase(String testCaseDisplayId, ExecutionStatus executionStatus) {

        if (!testCaseDisplayId.isEmpty()) {
            log.info("Test case display id was not provided for this test.");
            return;
        }

        TestCase testCase = api.getTestCaseByExternalId(testCaseDisplayId, null);
        if ( testCase == null ) {
            log.error("Cant find TestLink testCase by externalId=" + testCaseDisplayId);
            return;
        }

        api.reportTCResult(testCase.getId(), null,
                testPlanId, executionStatus, buildId, null, null, null, null, null, null, null, null);
        log.info("Test Case results update status : " + executionStatus);
    }
}

