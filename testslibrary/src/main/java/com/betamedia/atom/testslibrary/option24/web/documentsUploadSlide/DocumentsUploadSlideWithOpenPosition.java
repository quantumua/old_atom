package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.Assert;
import org.testng.annotations.*;

public class DocumentsUploadSlideWithOpenPosition extends DocumentsUploadSlideFunctionalityTest {

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
        createUser(countrycode,phonecountryprefix,"1500");
        closeWizardAndGoTrade();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        openPosition("0.01");
        softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "No any position opened verification");
        pages().assets().leavePandaFrame();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .invoke()
                .poiUploadPassport(POI_PASSPORT_PATH)
                .porUploadGasBill(POR_GAS_BILL_PATH);
        pages().topNavigationPage().trade();
        openPosition("0.01");
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
        pages().assets().leavePandaFrame();
    }

    /*
     * [TestLink] CTW-5346:Deposit > 2000$ restricted account before POR submit
     */
    @Test(description = "CTW-5346:Deposit > 2000$ restricted account before POR submit")
    @TestLinkProperties(displayId = "CTW-5346")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositMoreThan2000RestrictedAccountBeforePORSubmit(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUser(countrycode,phonecountryprefix,"1500");
        pages().uploadDocumentDialog().poiUploadPassport(POI_PASSPORT_PATH);
        pages().thankYouPage().startTrade();
        pages().setLeverageDialog().selectLeverage("100");
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        openPosition("0.01");
        softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "No any position opened verification");
        pages().assets().leavePandaFrame();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .invoke()
                .porUploadGasBill(POR_GAS_BILL_PATH);
        pages().topNavigationPage().trade();
        openPosition("0.01");
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
    }

    /*
     * [TestLink] CTW-5362:Multiple deposits without POI + POR - amount is less then restriction policy
     */
    @Test(description = "CTW-5362:Multiple deposits without POI + POR - amount is less then restriction policy")
    @TestLinkProperties(displayId = "CTW-5362")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void multipleDepositsWithoutPOIAndPORAmountIsLessThenRestrictionPolicy(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUser(countrycode,phonecountryprefix,"390");
        closeWizardAndGoToMyAccount();
        //1 - Do not upload a document at POI then make additional deposit amount 500$
        makeDepositOpenPositionVerifySuccess("390");
        //      3 - Deposit additional 950$ (QD) than back to trade while amount isn't higher than 2000$ and try to open position
        makeDepositOpenPositionVerifySuccess("750");
        //      4 - Deposit additional 950$ (QD) than back to trade while amount isn't higher than 2000$ and tru to open position
        makeDepositOpenPositionVerifySuccess("40");
   }

    /*
     * CTW-5363:Multiple deposits without POI + POR - amount is over than restriction policy
     */
    @Test(description = "CTW-5363:Multiple deposits without POI + POR - amount is over than restriction policy")
    @TestLinkProperties(displayId = "CTW-5363")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void multipleDepositsWithoutPOIAndPORAmountIsOverThanRestrictionPolicy(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUser(countrycode,phonecountryprefix,"390");
        closeWizardAndGoTrade();
        makeDepositOpenPositionVerifyFailure("2000");
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .invoke()
                .poiUploadPassport(POI_PASSPORT_PATH)
                .verifyPOIDocumentsUploaded(1);
        pages().uploadDocumentsTab()
                .porUploadGasBill(POR_GAS_BILL_PATH)
                .verifyPORDocumentsUploaded(1);
        verifyPOIStatusInCRM(OCR_STATUS_VERIFIED);
        pages().topNavigationPage().trade();
        openPosition("0.01");
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
        pages().assets().leavePandaFrame();
    }

    /*
     * CTW-5348:Multiple deposits without POI + POR - amount is less then restriction policy
     */
    @Test(description = "CTW-5348:Multiple deposits without POI + POR - amount is less then restriction policy")
    @TestLinkProperties(displayId = "CTW-5348")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void multipleDepositsWithoutPOIAndPORAmountIsLessThenRestrictionPolicy_5348(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUser(countrycode, phonecountryprefix, "390");
        pages().uploadDocumentDialog()
                .poiUploadPassport(POI_PASSPORT_PATH);
        pages().thankYouPage().startTrade();
        pages().setLeverageDialog().selectLeverage("100");
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        makeDepositOpenPositionVerifySuccess("390");
        makeDepositOpenPositionVerifySuccess("740");
        makeDepositOpenPositionVerifySuccess("40");
    }

    /*
     * CTW-5349:Multiple deposits without POI + POR - amount is over than restriction policy
    */
    @Test(description = "CTW-5349:Multiple deposits without POI + POR - amount is over than restriction policy")
    @TestLinkProperties(displayId = "CTW-5349")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void multipleDepositsWithoutPOIAndPORAmountIsOverThanRestrictionPolicy_5349(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUser(countrycode,phonecountryprefix,"390");
        pages().uploadDocumentDialog()
                .poiUploadPassport(POI_PASSPORT_PATH);
        pages().thankYouPage().startTrade();
        pages().setLeverageDialog().selectLeverage("100");
        makeDepositOpenPositionVerifyFailure("2000");
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab()
                .porUploadGasBill(POR_GAS_BILL_PATH)
                .verifyPORDocumentsUploaded(1);
        verifyPORStatusInCRM(OCR_STATUS_VERIFIED);
        pages().topNavigationPage().trade();
        openPosition("0.01");
        softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
        pages().assets().leavePandaFrame();
    }

    /*
     * CTW-5350:Set POR before FNS + Knowledge - Logout/Login
     */
    @Test(description = "CTW-5350:Set POR before FNS + Knowledge - Logout/Login")
    @TestLinkProperties(displayId = "CTW-5350")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void setPORBeforeFNSKnowledgeLogoutLogin(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        CustomerRegistrationInfo customerRegistrationInfo = createUser(countrycode,phonecountryprefix,"50");
        pages().uploadDocumentDialog()
                .porUploadElectricityBill(POR_ELECTRICITY_BILL_PATH)
                .exists();
        verifyPORStatusInCRM(OCR_STATUS_VERIFIED, customerRegistrationInfo);
        closeWizardAndGoTrade();
        pages().controlPanel().logOut();
        pages().topNavigationPage().waitForLoggedOut();
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(customerRegistrationInfo.getEmail(),customerRegistrationInfo.getPassword());
        softAssert().assertTrue(pages().uploadDocumentDialog().exists(), "Upload Documents page verification availability verification");
    }

    /*
     * CTW-5364:Set POI before FNS + Knowledge - Logout/Login
     */
    @Test(description = "CTW-5364:Set POI before FNS + Knowledge - Logout/Login")
    @TestLinkProperties(displayId = "CTW-5364")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void setPOIBeforeFNSKnowledgeLogoutLogin(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        CustomerRegistrationInfo customerRegistrationInfo = createUser(countrycode,phonecountryprefix,"50");
        pages().uploadDocumentDialog()
                .poiUploadIdCard(POI_ID_FRONT_PATH, POI_ID_BACK_PATH)
                .exists();
        verifyPOIStatusInCRM(OCR_STATUS_VERIFIED, customerRegistrationInfo);
        closeWizardAndGoTrade();
        pages().controlPanel().logOut();
        pages().topNavigationPage().waitForLoggedOut();
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(customerRegistrationInfo.getEmail(),customerRegistrationInfo.getPassword());
        softAssert().assertTrue(pages().uploadDocumentDialog().exists(), "Upload Documents page verification availability verification");
    }


    private void makeDepositOpenPositionVerifySuccess(String deposit) {
       // 1 - deposit deposi
       makeAdditionalDeposit (deposit);
       // 2 - try to open position
       openPosition("0.01");
       // 3 - Verify success
       softAssert().assertTrue(pages().cfdPositions().isAnyPositionOpened(), "One new position opened verification");
       pages().assets().leavePandaFrame();
   }

    private void makeDepositOpenPositionVerifyFailure(String deposit) {
       // 1 - deposit deposi
       makeAdditionalDeposit (deposit);
       // 2 - try to open position
       openPosition("0.01");
       // 3 - Verify failure
       softAssert().assertFalse(pages().cfdPositions().isAnyPositionOpened(), "No any new position opened verification");
       pages().assets().leavePandaFrame();
    }

    private void makeAdditionalDeposit (String deposit) {
        pages().topNavigationPage().deposit();
        pages().creditCardDepositPage().submit(CreditCardDeposit.builder()
                .withDepositAmount(deposit)
                .build());
    }

    private void openPosition (String amount) {
        pages().assets().switchToPanda();
        pages().cfdBidder()
                .setAmount(amount)
                .buy()
                .confirm();
        pages().messageBox().ok();
    }
}
