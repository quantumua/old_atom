package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;
import com.betamedia.qe.af.core.testingtype.tp.TPBackEndTest;
import com.betamedia.qe.af.core.utils.StringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public class MobileCRMMarketingParametersTest extends TPBackEndTest {

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
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = getExpectedMarketingParametersBuilder(useLongAliases);
        marketingParametersBuilder.setKeyword(keyword);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder, marketingParametersBuilder);

        assertNotNull(registeredCustomer);
        verifyExpectedMarketingParameters(keyword);
    }

    @Test
    public void testRegistrationWithAfSiteIdRemoved() {
        final String keyword = StringUtils.generateRandomId(10);
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = getExpectedMarketingParametersBuilder(true);
        marketingParametersBuilder.setAf_siteid("https://www.24option.com/");
        marketingParametersBuilder.setSiteId(null);
        marketingParametersBuilder.setKeyword(keyword);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder, marketingParametersBuilder);
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
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        MarketingParametersBuilder marketingParametersBuilder = getExpectedMarketingParametersBuilder(useLongAliases);
        marketingParametersBuilder.setKeyword(keyword);

        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder, marketingParametersBuilder);

        assertNotNull(deposit);
        verifyExpectedMarketingParameters(keyword);
    }

    private MarketingParametersBuilder getExpectedMarketingParametersBuilder(boolean useLongAliases) {
        MarketingParametersBuilder marketingParametersBuilder =  new MarketingParametersBuilder(useLongAliases);
        marketingParametersBuilder.setReferrer(referrer);
        marketingParametersBuilder.setSiteId(siteId);
        marketingParametersBuilder.setOfrtc(ofrtc);
        marketingParametersBuilder.setParam1(param1);
        marketingParametersBuilder.setParam2(param2);
        marketingParametersBuilder.setParam3(param3);
        marketingParametersBuilder.setParam4(param4);
        marketingParametersBuilder.setParam5(param5);
        return marketingParametersBuilder;
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
