package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
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
        softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "No any position opened verification");
        pages().assets().leavePandaFrame();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .invoke()
                .poiUploadPassport(POI_PASSPORT_PATH)
                .porUploadGasBill(POR_GAS_BILL_PATH);
        pages().topNavigationPage().cfd();
        pages().assets().switchToPanda();
        pages().cfdBidder()
                .setAmount("0.01")
                .buy()
                .confirm();
        pages().messageBox().ok();
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
    }

    /*
     * [TestLink] CTW-5346:Deposit > 2000$ restricted account before POR submit
     */
    @Test(description = "CTW-5346:Deposit > 2000$ restricted account before POR submit")
    @TestLinkProperties(displayId = "CTW-5346")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositMoreThan2000RestrictedAccountBeforePORSubmit(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserByUI(countrycode,phonecountryprefix,"1500");
        pages().uploadDocumentDialog().poiUploadPassport(POI_PASSPORT_PATH);
        pages().thankYouPage().startTrade();
        pages().setLeverageDialog().selectLeverage("100");
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().assets().switchToPanda();
        pages().cfdBidder()
                .setAmount("0.01")
                .buy()
                .confirm();
        pages().messageBox().ok();
        softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "No any position opened verification");
        pages().assets().leavePandaFrame();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .invoke()
                .porUploadGasBill(POR_GAS_BILL_PATH);
        pages().topNavigationPage().trade();
        pages().assets().switchToPanda();
        pages().cfdBidder()
                .setAmount("0.01")
                .buy()
                .confirm();
        pages().messageBox().ok();
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
    }
}
