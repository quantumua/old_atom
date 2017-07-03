package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.operations.TradingCalendarOperations;
import com.betamedia.atom.core.dsl.operations.VolatilityUnitOperations;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.RelatedEntityHolder;
import com.betamedia.tp.api.model.enums.AssetStatus;
import com.betamedia.tp.api.model.enums.Market;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import com.betamedia.tp.api.service.feedgateway.IFeedService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 4/19/17.
 */
public class AssetOperationsTest {
    private static class QAEnvAssetOperationsImpl extends AbstractAssetOperations<QAEnvironment> implements QAEnvironment {}

    @InjectMocks
    private QAEnvAssetOperationsImpl assetOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private FWFeedGatewayConnector feedGatewayConnector;

    @Mock
    private TradingCalendarOperations tradingCalendarOperations;

    @Mock
    private VolatilityUnitOperations volatilityUnitOperations;

    @Mock
    private IFeedService feedService;

    private String assetId = "testAssetId";

    private String calendarId = "testCalendarId";
    private String calendarDisplayId = "testCalendarDisplayId";

    private int roundFactor = 5;
    private double sigma = 0.002;
    private double factor = 1.0;
    private double threshold = 1.0;
    private double restriction = 1.0;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(tradingCalendarOperations.get()).thenReturn(getExpectedTradingCalendar());
        when(feedGatewayConnector.serviceProxy(IFeedService.class)).thenReturn(feedService);
        when(tpConnector.readById(Asset.class, assetId)).thenReturn(getExpectedAsset());

        doAnswer(invocationOnMock -> {
            Asset asset = invocationOnMock.getArgumentAt(0, Asset.class);
            asset.setId(assetId);
            return asset;
        }).when(tpConnector).create(any(Asset.class));
    }

    @Test
    public void testGetNewAsset() {
        Asset actualAsset = assetOperations.get();
        RelatedEntityHolder actualCalendar = actualAsset.getTradingCalendar();

        verify(volatilityUnitOperations).generateForAsset(assetId, sigma, factor, threshold, restriction);
        verify(feedService).setRoundFactor(assetId, roundFactor);
        assertThat(getExpectedAsset(), new ReflectionEquals(actualAsset, "id"));
        assertEquals(calendarId, actualCalendar.getId());
        assertEquals(calendarDisplayId, actualCalendar.getDisplayId());
    }

    @Test
    public void testGetAssetById() {
        Asset actualAsset = assetOperations.get(assetId);
        assertThat(getExpectedAsset(), new ReflectionEquals(actualAsset));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testGetUnavailableAsset() {
        assetOperations.get("unavailable");
    }

    private Asset getExpectedAsset() {
        Asset asset = new Asset();
        asset.setAssetName("QEAUTOTEST");
        asset.setMarket(Market.INDICES);
        asset.setTradingCalendar(getExpectedTradingCalendar());
        int feedTimeout = 500;
        asset.setFeedTimeout(feedTimeout);
        asset.setFeedTimeoutAlert(feedTimeout + 1);
        asset.setPipSize(0.001);
        asset.setRoundFactor(roundFactor);
        asset.setStatus(AssetStatus.ACTIVE);
        asset.setId(assetId);
        return asset;
    }

    private TradingCalendar getExpectedTradingCalendar() {
        TradingCalendar tradingCalendar = new TradingCalendar();
        tradingCalendar.setId(calendarId);
        tradingCalendar.setDisplayId(calendarDisplayId);
        return tradingCalendar;
    }
}
