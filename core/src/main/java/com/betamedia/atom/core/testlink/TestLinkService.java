package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.TestLinkListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Service to provide TestLink integration.
 * It is used to update test result status in conjunction with {@link TestLinkListener}.
 * <p>
 * Created by Oleksandr Losiev on 5/16/17.
 */
@Service
public class TestLinkService {

    private static final Logger log = LogManager.getLogger(TestLinkService.class);
    @Autowired
    private TestLinkProperties testLinkProperties;
    private TestLinkAPI api;

    @PostConstruct
    public void init() throws MalformedURLException {
        api = new TestLinkAPI(new URL(testLinkProperties.getUrl()), testLinkProperties.getKey());
    }

    public void updateTestCase(TestCaseResult tcRes) {
        ExecutionStatus executionStatus = tcRes.getStatus();
        TestCase testCase = api.getTestCaseByExternalId(tcRes.getDisplayId(), null);
        api.reportTCResult(testCase.getId(), null,
                tcRes.getPlanId(), executionStatus,
                tcRes.getBuildId(), null,
                tcRes.getNotes(), null, null, null,
                null, null, null);
        log.info("Updated test case result in TestLink " + tcRes);
    }
}

