package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareClientTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class FaultyAssetsTest extends TPResourceAwareClientTest {

    //expected to fail
    @Test
    public void faultyAssetsTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("tp_automation_uku5ln", CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        List<String> assets = pages().assets().cfdAssetNames();
        Assert.assertEqualsNoOrder(
                assets.toArray(),
                getResources(ExpectedCfdAsset.class)
                        .stream()
                        .map(ExpectedCfdAsset::getSymbol)
                        .collect(Collectors.toList())
                        .toArray());
    }

    //WIP
    @Test
    public void currencyTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("tp_automation_uku5ln", CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        String expectedCurrency = pages().controlPanel().getCurrency();
        pages().assets().cfdValidateAssets(getResources(ExpectedCfdAsset.class), expectedCurrency);
    }
}
