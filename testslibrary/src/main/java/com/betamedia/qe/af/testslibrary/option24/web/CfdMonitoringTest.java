package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
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

    /**
     * expected to fail <br/>
     * currently provided list of expected assets does not match the data on the page
     **/
    @Test
    public void faultyAssetsTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("tp_automation_spjlto", CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        List<String> assets = pages().assets().cfdAssetNames();
        assets.removeAll(getExpected());
        assertThat("Found unexpected assets:" + String.join(",", assets), assets, is(empty()));
    }

    /**
     * expected to fail <br/>
     * currently provided list of expected assets does not match the data on the page
     **/
    @Test
    @Parameters({"username", "password"})
    public void assetValidationAndCurrencyConversionTest(String username, String password) {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(username, password);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        String expectedCurrency = pages().controlPanel().getCurrency();
        pages().assets().cfdValidateAssets(getResources(ExpectedCfdAsset.class), expectedCurrency);
    }

    /**
     * parametrized trading test that uses pre-existing credentials for account that is able to make CFD trades
     **/
    @Test
    @Parameters({"username", "password"})
    public void parametrizedBiddingTest(String username, String password) {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(username, password);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().assets().forEachCfdAssetClicked(symbol -> {
                    pages().cfdBidder().setAmount("0.01").buy().confirm();
                    pages().messageBox().ok();
                    Assert.assertEquals(pages().cfdPositions().getSymbolForLatest(), symbol);
                }
        );
    }

    private Set<String> getExpected() {
        return getResources(ExpectedCfdAsset.class)
                .stream()
                .map(ExpectedCfdAsset::getSymbol)
                .collect(Collectors.toSet());
    }

}
