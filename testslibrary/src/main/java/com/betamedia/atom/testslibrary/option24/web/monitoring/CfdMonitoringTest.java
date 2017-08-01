package com.betamedia.atom.testslibrary.option24.web.monitoring;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.atom.core.testingtype.web.WebClientTest;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

/**
 * @author mbelyaev
 * @since 4/18/17
 */
public class CfdMonitoringTest extends WebClientTest {
    private static final String USERNAME = "QENirShuTest@Test.ru";
    private static final String PASSWORD = CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD;
    private static final String DATA_DRIVEN_MONITORING_TEST_GROUP = "data_driven_monitoring_test_group";
    private String expectedCurrency;

    /**
     * Fails if the asset list on CFD page has entries not contained in list of expected assets
     */
    @Test
    public void unexpectedAssetsTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(USERNAME, PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        List<String> assets = pages().assets().cfdAssetNames();
        assets.removeAll(getExpected());
        assertThat("Found unexpected assets:" + String.join(",", assets), assets, is(empty()));
    }

    /**
     * Get list of list names of expected assets
     */
    private Set<String> getExpected() {
        return getResources(ExpectedCfdAsset.class)
                .stream()
                .map(ExpectedCfdAsset::getListBidderName)
                .collect(Collectors.toSet());
    }

    /**
     * One-time setup for asset validation test cycle (logs in and gets expected currency)
     */
    @BeforeGroups(DATA_DRIVEN_MONITORING_TEST_GROUP)
    public void prepareOnce() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(USERNAME, PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        expectedCurrency = pages().controlPanel().getCurrency();
        pages().assets().switchToPanda();
    }

    /**
     * For each entry in list of expected CFD assets, find it on product page, validate symbols and currency and try to open a trade <br/>
     * Product page is expected to not contain every expected CFD asset.
     */
    @Test(dataProvider = CACHED_DATA_PROVIDER, groups = DATA_DRIVEN_MONITORING_TEST_GROUP)
    public void assetValidationTest(ExpectedCfdAsset asset) {
        if (!pages().assets().tryValidateAsset(asset.getListBidderName(), asset.getSymbol(), asset.getTooltipName(), expectedCurrency)) {
            return;
        }
        pages().cfdBidder().setAmount("0.01").buy().confirm();
        pages().messageBox().ok();
        pages().cfdPositions().validateLatestPosition(asset.getListBidderName());
    }

    @Override
    protected boolean doResetState() {
        return false;
    }

    @Override
    protected Class getDataSourceEntity() {
        return ExpectedCfdAsset.class;
    }
}
