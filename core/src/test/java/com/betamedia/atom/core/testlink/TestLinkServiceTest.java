package com.betamedia.atom.core.testlink;

import com.betamedia.atom.core.persistence.entities.TestLinkTestCase;
import com.betamedia.atom.core.persistence.repositories.impl.testlink.TestLinkTestCaseRepository;
import com.betamedia.atom.core.testlink.TestCaseResultStatus;
import com.betamedia.atom.core.testlink.TestLinkService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIClient;

import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 5/17/17.
 */
public class TestLinkServiceTest {

    private int planId = 5125;
    private int buildId = 632;
    private String testCaseDisplayId = "displayId-838";
    private Long numericalDisplayId = 838L;
    private String invalidDisplayId = "invalidDisplayId";
    private Integer testCaseId = 125;
    private Long automationExecutionType = 2L;

    @InjectMocks
    private TestLinkService testLinkService;

    @Mock
    private TestLinkTestCaseRepository testCaseRepository;

    @Mock
    private TestLinkAPIClient testLinkAPIClient;

    @Mock
    private TestLinkTestCase testCase;

    @BeforeClass
    public void setupClass() throws Exception {
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(testLinkService, "testPlanId", planId);
        ReflectionTestUtils.setField(testLinkService, "buildId", buildId);
        ReflectionTestUtils.setField(testLinkService, "api", testLinkAPIClient);
        when(testCaseRepository.findByExternalIdAndExecutionType(numericalDisplayId, automationExecutionType)).thenReturn(Collections.singletonList(testCase));
        when(testCase.getId()).thenReturn(testCaseId);
    }

    @Test
    public void testUpdateTestCase() throws Exception {
        testLinkService.updateTestCase(testCaseDisplayId, TestCaseResultStatus.PASSED);
        verify(testLinkAPIClient).reportTestCaseResult(planId, testCaseId, buildId, "", TestCaseResultStatus.PASSED.getStatus());
    }

    @Test
    public void testUpdateFailedTestCase() throws Exception {
        testLinkService.updateTestCase(testCaseDisplayId, TestCaseResultStatus.FAILED);
        verify(testLinkAPIClient).reportTestCaseResult(planId, testCaseId, buildId, "", TestCaseResultStatus.FAILED.getStatus());
    }

    @Test
    public void testUpdateWithoutDisplayId() throws Exception {
        testLinkService.updateTestCase(null, TestCaseResultStatus.FAILED);
        verify(testLinkAPIClient, never()).reportTestCaseResult(planId, testCaseId, buildId, "", TestCaseResultStatus.FAILED.getStatus());
    }

    @Test
    public void testUpdateWithInvalidDisplayId() throws Exception {
        testLinkService.updateTestCase(invalidDisplayId, TestCaseResultStatus.PASSED);
        verify(testLinkAPIClient, never()).reportTestCaseResult(planId, testCaseId, buildId, "", TestCaseResultStatus.PASSED.getStatus());
    }

    @Test
    public void testUpdateWithNotFoundInternalId() throws Exception {
        when(testCaseRepository.findByExternalIdAndExecutionType(numericalDisplayId, automationExecutionType)).thenReturn(Collections.emptyList());
        testLinkService.updateTestCase(testCaseDisplayId, TestCaseResultStatus.FAILED);
        verify(testLinkAPIClient, never()).reportTestCaseResult(planId, testCaseId, buildId, "", TestCaseResultStatus.FAILED.getStatus());
    }

}