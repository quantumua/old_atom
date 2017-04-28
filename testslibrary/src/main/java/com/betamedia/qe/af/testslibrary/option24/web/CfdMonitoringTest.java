package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        Assert.assertEqualsNoOrder(
                assets.toArray(),
                getResources(ExpectedCfdAsset.class)
                        .stream()
                        .map(ExpectedCfdAsset::getSymbol)
                        .collect(Collectors.toList())
                        .toArray());
    }


    /**
     * expected to fail <br/>
     * currently provided list of expected assets does not match the data on the page
     **/
    @Test
    public void assetValidationAndCurrencyConversionTest() {
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount cfdAccount = customer.getFXCFDAccount();
        operations().accountOperations().depositCRM(cfdAccount.getExternalId(), 1000d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        String expectedCurrency = pages().controlPanel().getCurrency();
        pages().assets().cfdValidateAssets(getResources(ExpectedCfdAsset.class), expectedCurrency);
    }

    /**
     * expected to fail <br/>
     * currently there is no way to set CFD/Panda account balance for the customer - we are unable to test proper trading flow
     **/
    @Test
    public void tradingTest() {
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount cfdAccount = customer.getFXCFDAccount();
        operations().accountOperations().depositCRM(cfdAccount.getExternalId(), 1000d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().assets().forEachAssetClicked(symbol -> {
            pages().cfdBidder().setAmount("0.01").buy().confirm();
            pages().messageBox().ok();
            Assert.assertEquals(pages().cfdPositions().getSymbolForLatest(), symbol);
        });

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

}
