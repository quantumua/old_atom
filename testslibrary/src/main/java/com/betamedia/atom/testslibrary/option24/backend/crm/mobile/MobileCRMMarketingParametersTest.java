package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.persistence.entities.TrackingInfoExtension;
import com.betamedia.atom.core.testingtype.web.WebBackEndTest;
import com.betamedia.atom.core.utils.StringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public class MobileCRMMarketingParametersTest extends WebBackEndTest {

    private String referrer = "http://www.testReferrer.com";
    private String siteId = "https://www.24option.com/landing/competition/index.cshtml?lang%3Dfr";
    private String param1 = "1111";
    private String param2 = "2222";
    private String param3 = "3333";
    private String param4 = "4444";
    private String param5 = "5555";
    private String ofrtc = "12345";

    @Test
    public void testRegistrationWithParamXAliases() {
        testRegistrationAliases(true);
    }

    @Test
    public void testRegistrationWithPXAliases() {
        testRegistrationAliases(false);
    }

    private void testRegistrationAliases(boolean useLongAliases) {
        final String keyword = StringUtils.generateRandomId(10);

        CRMCustomer registeredCustomer = operations().customerOperations().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build(),
                getExpectedMarketingParametersBuilder(useLongAliases)
                        .setKeyword(keyword).build());

        assertNotNull(registeredCustomer);
        verifyExpectedMarketingParameters(keyword);
    }

    @Test
    public void testRegistrationWithAfSiteIdRemoved() {
        final String keyword = StringUtils.generateRandomId(10);

        CRMCustomer registeredCustomer = operations().customerOperations().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build(),
                getExpectedMarketingParametersBuilder(true)
                        .setAf_siteid("https://www.24option.com/")
                        .setSiteId(null)
                        .setKeyword(keyword)
                        .build());
        assertNotNull(registeredCustomer);
        TrackingInfoExtension infoExtension = operations().customerOperations().getCustomerTrackingInfoExtensionByCustomerId(registeredCustomer.getId());
        assertNotNull(infoExtension);

        assertNull(infoExtension.getSiteId());
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithParamXAliases(String tradingAccountId) {
        testDepositAliases(tradingAccountId, true);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithPXAliases(String tradingAccountId) {
        testDepositAliases(tradingAccountId, false);
    }

    private void testDepositAliases(String tradingAccountId, boolean useLongAliases) {
        final String keyword = StringUtils.generateRandomId(10);

        CRMDeposit deposit = operations().customerOperations().deposit(
                MobileDepositRO.builder(tradingAccountId).build(),
                getExpectedMarketingParametersBuilder(useLongAliases)
                        .setKeyword(keyword).build());

        assertNotNull(deposit);
        verifyExpectedMarketingParameters(keyword);
    }

    //TODO: these are fields of CustomerRO! Need to refactor
    private MarketingParametersRO.MarketingParametersBuilder getExpectedMarketingParametersBuilder(boolean useLongAliases) {
        return MarketingParametersRO.builder(useLongAliases)
                .setReferrer(referrer)
                .setSiteId(siteId)
                .setOfrtc(ofrtc)
                .setParam1(param1)
                .setParam2(param2)
                .setParam3(param3)
                .setParam4(param4)
                .setParam5(param5);
    }

    private void verifyExpectedMarketingParameters(String keyword) {
        TrackingInfoExtension infoExtension = operations().customerOperations().getCustomerTrackingInfoExtensionByKeyword(keyword);
        assertNotNull(infoExtension);
        verifyExpectedMarketingParameters(infoExtension);
    }

    private void verifyExpectedMarketingParameters(TrackingInfoExtension infoExtension) {
        assertEquals(referrer, infoExtension.getReferrer());
        assertEquals(siteId, infoExtension.getSiteId());
        assertEquals(ofrtc, infoExtension.getTrackingCode());
        assertEquals(param1, infoExtension.getParam1());
        assertEquals(param2, infoExtension.getParam2());
        assertEquals(param3, infoExtension.getParam3());
        assertEquals(param4, infoExtension.getParam4());
        assertEquals(param5, infoExtension.getParam5());
    }
}
