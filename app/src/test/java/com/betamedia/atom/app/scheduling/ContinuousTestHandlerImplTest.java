package com.betamedia.atom.app.scheduling;

import com.betamedia.atom.app.executor.AsyncTestExecutor;
import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.handler.TestInformationHandler;
import com.betamedia.atom.app.reporting.EmailService;
import com.betamedia.atom.app.scheduling.impl.ContinuousTest;
import com.betamedia.atom.app.scheduling.impl.ContinuousTestHandlerImpl;
import com.betamedia.atom.app.storage.TempStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author mbelyaev
 * @since 6/27/17
 */
@RunWith(MockitoJUnitRunner.class)
public class ContinuousTestHandlerImplTest {
    @InjectMocks
    private ContinuousTestHandlerImpl continuousTestHandler;
    @Mock
    private AsyncTestExecutor asyncTestExecutor;
    @Mock
    private TempStorageService storageService;
    @Mock
    private EmailService emailService;
    @Mock
    private ContinuousTestFactory continuousTestFactory;
    @Mock
    private TestInformationHandler testInformationHandler;
    @Mock
    private ContinuousTest mockTest;
    @Mock
    private ListenableFuture<TestInformation> mockFuture;
    @Captor
    private ArgumentCaptor<MultipartFile> fileCaptor = ArgumentCaptor.forClass(MultipartFile.class);
    @Captor
    private ArgumentCaptor<Optional<String>> optCaptor = new ArgumentCaptor<>();
    @Captor
    private ArgumentCaptor<TestInformation> infoCaptor = ArgumentCaptor.forClass(TestInformation.class);
    @Captor
    private ArgumentCaptor<Supplier<ListenableFuture<TestInformation>>> testCaptor = new ArgumentCaptor<>();
    @Captor
    private ArgumentCaptor<SuccessCallback<TestInformation>> successCallbackCaptor = new ArgumentCaptor<>();
    @Captor
    private ArgumentCaptor<FailureCallback> failureCallbackCaptor = ArgumentCaptor.forClass(FailureCallback.class);

    @Test
    public void testHandleTest() throws Exception {
        String name = "testName";
        Properties properties = new Properties();
        properties.setProperty("key", "value");
        MultipartFile mockSuite = mock(MultipartFile.class);
        String suitePath = "suitePath";
        Optional<String> cronExpression = Optional.empty();
        String emailAddress = "sampleAddress";
        String sampleEmailReportURL = "sampleEmailReportURL";
        String sampleAttachmentURL = "sampleAttachmentURL";

        when(storageService.storeToTemp(fileCaptor.capture(), anyString())).thenReturn(suitePath);
        when(continuousTestFactory.get(testCaptor.capture(), optCaptor.capture())).thenReturn(mockTest);

        List<TestInformation> results = continuousTestHandler.handleTest(name, properties, new MultipartFile[]{mockSuite}, cronExpression, emailAddress);

        verify(testInformationHandler).put(infoCaptor.capture());
        verify(continuousTestFactory).get(testCaptor.capture(), optCaptor.capture());
        verify(mockTest).start();

        assertThat(results.size(), is(1));
        TestInformation startInfo = results.get(0);
        assertThat(startInfo, is(infoCaptor.getValue()));
        assertThat(startInfo.name, is(name));
        assertThat(startInfo.properties, is(properties));
        assertThat(startInfo.suites, containsInAnyOrder(suitePath));

        when(asyncTestExecutor.submit(startInfo, cronExpression)).thenReturn(mockFuture);
        testCaptor.getValue().get();

        verify(mockFuture).addCallback(successCallbackCaptor.capture(), failureCallbackCaptor.capture());
        SuccessCallback<TestInformation> successCallback = successCallbackCaptor.getValue();
        reset(testInformationHandler);
        TestInformation processedInfo = startInfo.update().hasFailed(true).withEmailReportURL(sampleEmailReportURL).withAttachmentURLs(Collections.singletonList(sampleAttachmentURL)).build();
        successCallback.onSuccess(processedInfo);
        verify(testInformationHandler).put(processedInfo);
        verify(emailService).sendLocalFile(emailAddress, name, processedInfo.emailReportURL, processedInfo.attachmentURLs);
    }

    @Test
    public void testStop() throws Exception {
    }

    @Test
    public void testAbort() throws Exception {
    }

    @Test
    public void testGetInfo() throws Exception {
    }

}