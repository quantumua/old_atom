package com.betamedia.qe.af.testslibrary.option24.web.monitoring;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
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
 * Created by mbelyaev on 4/18/17.
 */
public class CfdMonitoringTest extends TPResourceAwareEndToEndTest {
    private final static String USERNAME = "QENirShuTest@Test.ru";
    private final static String PASSWORD = CustomerBuilder.PASSWORD;
    private final static String DATA_DRIVEN_MONITORING_TEST_GROUP = "data_driven_monitoring_test_group";
    private String expectedCurrency;

    /**
     * One-time setup for asset validation test cycle (logs in and gets expected currency)
     */
    @BeforeGroups(DATA_DRIVEN_MONITORING_TEST_GROUP)
    public void prepareOnce() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(USERNAME, PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        expectedCurrency = pages().controlPanel().getCurrency();
        pages().assets().switchToPanda();
    }

    /**
     * Fails if the asset list on CFD page has entries not contained in list of expected assets
     */
    @Test
    public void unexpectedAssetsTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(USERNAME, PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        List<String> assets = pages().assets().cfdAssetNames();
        assets.removeAll(getExpected());
        assertThat("Found unexpected assets:" + String.join(",", assets), assets, is(empty()));
    }

    /**
     * For each entry in list of expected CFD assets, find it on product page, validate symbols and currency and try to open a trade <br/>
     * Product page is expected to not contain every expected CFD asset.
     */
    @Test(dataProvider = "ExpectedCfdAssetDataProvider", groups = DATA_DRIVEN_MONITORING_TEST_GROUP)
    public void assetValidationTest(String listName, String symbol, String tooltipName) {
        if (!pages().assets().tryValidateAsset(listName, symbol, tooltipName, expectedCurrency)) {
            return;
        }
        pages().cfdBidder().setAmount("0.01").buy().confirm();
        pages().messageBox().ok();
        pages().cfdPositions().validateLatestPosition(listName);
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

    @Override
    public boolean keepBrowser(){
        return true;
    }
}
