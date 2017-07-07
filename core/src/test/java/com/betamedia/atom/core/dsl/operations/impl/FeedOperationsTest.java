package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.common.utils.UUIDUtils;
import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.model.enums.FeedResolution;
import com.betamedia.tp.api.service.feedgateway.IFeedService;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class FeedOperationsTest {
    private static class QAEnvFeedOperationsImpl extends AbstractFeedOperations<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvFeedOperationsImpl feedOperations;

    @Mock
    private FWFeedGatewayConnector feedGatewayConnector;

    @Mock
    private IFeedService feedService;

    private TickData expectedTickData;
    private String assetId = "testAssetId";
    private double price = 25d;

    @BeforeClass
    public void setupClass() {
        expectedTickData = getExpectedTickData();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(feedGatewayConnector.serviceProxy(IFeedService.class)).thenReturn(feedService);
    }

    @Test
    public void testInjectFeed() {
        testFeed(assetId, price);
    }

    @Test
    public void testInjectFeedWithEmptyId() {
        testFeed("", price);
    }

    @Test
    public void testInjectFeedWithNegativePrice() {
        testFeed(assetId, -price);

    }

    private void testFeed(String assetId, double price) {
        ArgumentCaptor<TickData> argumentCaptor = ArgumentCaptor.forClass(TickData.class);

        feedOperations.injectFeed(assetId, price);

        verify(feedService).simulateTicks(argumentCaptor.capture());
        TickData actualTickData = argumentCaptor.getValue();
        assertEquals(assetId, actualTickData.getAssetID());
        assertEquals(price, actualTickData.getSpot(), 0.01f);
    }

    private TickData getExpectedTickData() {
        TickData data = new TickData();
        data.setId(UUIDUtils.getUUID());
        data.setAssetID("EURUSD");
        data.setFeedResolution(FeedResolution.RAW);
        data.setCreationTime(Long.valueOf(System.currentTimeMillis()));
        data.setDisplaySpot("$");
        data.setVolatility(Double.valueOf(0.0D));
        return data;
    }
}