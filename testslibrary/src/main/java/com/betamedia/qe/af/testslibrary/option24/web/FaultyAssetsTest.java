package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareClientTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
        List<String> expectedAssets = getContent("expectedCfdAssets.txt");
        Assert.assertEqualsNoOrder(assets.toArray(), expectedAssets.toArray());
    }
}
