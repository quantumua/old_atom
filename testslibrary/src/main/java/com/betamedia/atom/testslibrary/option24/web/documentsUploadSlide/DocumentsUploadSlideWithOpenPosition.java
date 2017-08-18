package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DocumentsUploadSlideWithOpenPosition extends DocumentsUploadSlideSanityTest {

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
    }

    /*
    * [TestLink] CTW-5360:Deposit > 2000$ restricted account before POI submit
    */
    @Test(description = "CTW-5360:Deposit > 2000$ restricted account before POI submit")
    @TestLinkProperties(displayId = "CTW-5360")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositMoreThan2000RestrictedAccountBeforePOIsubmit(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserByUI(countrycode,phonecountryprefix,"1500");
        closeWizardAndGoTrade();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().assets().switchToPanda();

        pages().cfdBidder()
                .setAmount("0.01")
                .buy()
                .confirm();
        pages().messageBox().ok();
        ExpectedCfdAsset asset= null; //TODO: get from UI
        softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "Any position not opened");

        pages().assets().leavePandaFrame();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab().invoke();
        pages().uploadDocumentsTab().poiUploadPassport(POI_PASSPORT_PATH);
        pages().uploadDocumentsTab().porUploadGasBill(POR_GAS_BILL_PATH);

        pages().topNavigationPage().cfd();
        pages().assets().switchToPanda();
        pages().cfdBidder()
                .setAmount("0.01")
                .buy()
                .confirm();
        pages().messageBox().ok();
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "New position opened");
    }
}
