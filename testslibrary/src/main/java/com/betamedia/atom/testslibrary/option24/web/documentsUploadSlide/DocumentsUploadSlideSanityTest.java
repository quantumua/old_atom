package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */

public class DocumentsUploadSlideSanityTest extends AbstractOnboardingUserExperienceTest {

    static final String POI_PASSPORT_PATH = "files/sample_passport.jpg";

    static final String POI_DRIVER_LICENSE_FRONT_PATH = "files/sample_driver_license_front.jpg";
    static final String POI_DRIVER_LICENSE_BACK_PATH = "files/sample_driver_license_back.jpg";

    static final String POI_ID_FRONT_PATH = "files/sample_id_front.jpg";
    static final String POI_ID_BACK_PATH = "files/sample_id_back.jpg";

    static final String POR_ELECTRICITY_BILL_PATH = "files/sample_electricity_bill.jpg";
    static final String POR_GAS_BILL_PATH = "files/sample_gas_bill.jpg";

    private static final String CREDIT_CARD_FRONT_PATH = "files/sample_credit_card_front.jpg";
    private static final String CREDIT_CARD_BACK_PATH = "files/sample_credit_card_back.jpg";
    static final String WRONG_DOC_PATH = "files/sample_wrong_doc.jpg";

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserByUI(countrycode,phonecountryprefix,"50");
    }

    /*
     *[TestLink] CTW-5765:Successful upload document: POI section - Passport doc
     */
    @Test(description = "CTW-5765:Successful upload document: POI section - Passport doc")
    @TestLinkProperties(displayId = "CTW-5765")
    public void successfulUploadDocumentPOISectionPassportDoc() {
        pages().uploadDocumentDialog().poiUploadPassport(POI_PASSPORT_PATH);
        softAssert().assertFalse(pages().uploadDocumentDialog().poiBackImageExists(), "POI back image should not exists.");
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "Passport uploaded and user is ready to start Trade.");
    }

    /*
     * [TestLink] CTW-5766:Successful upload document: POR section
     */
    @Test(description = "CTW-5766:Successful upload document: POR section")
    @TestLinkProperties(displayId = "CTW-5766")
    public void successfulUploadDocumentPORSection() {
        pages().uploadDocumentDialog().porUploadElectricityBill(POR_ELECTRICITY_BILL_PATH);
        softAssert().assertTrue(pages().uploadDocumentDialog().porCheckmarkImageExists(), "Electricity bill uploaded successfully.");
    }

    /*
     *[TestLink] CTW-5767:Successful upload document: POI section - Driver license doc
     */
    @Test(description = "CTW-5767:Successful upload document: POI section - Driver license doc")
    @TestLinkProperties(displayId = "CTW-5767")
    public void successfulUploadDocumentPOISectionDriverLicenceDoc() {
        pages().uploadDocumentDialog().poiUploadDriverLicense(POI_DRIVER_LICENSE_FRONT_PATH, POI_DRIVER_LICENSE_BACK_PATH);
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "Driver license uploaded and user is ready to start Trade.");
    }

    /*
     *[TestLink] CTW-5768:Successful upload document: POI section - Identity card doc
     */
    @Test(description = "CTW-5768:Successful upload document: POI section - Identity card doc")
    @TestLinkProperties(displayId = "CTW-5768")
    public void successfulUploadDocumentPOISectionIdentityCardDoc() {
        pages().uploadDocumentDialog().uploadIdCard(POI_ID_FRONT_PATH, POI_ID_BACK_PATH);
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "ID Card uploaded and user is ready to start Trade.");
    }

    /*
     *[TestLink] CTW-5769:Successful upload document: Credit Card section
     */
    @Test(description = "CTW-5769:Successful upload document: Credit Card section")
    @TestLinkProperties(displayId = "CTW-5769")
    public void successfulUploadDocumentCreditCardSection() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().uploadCreditCard(CREDIT_CARD_FRONT_PATH, CREDIT_CARD_BACK_PATH);
        softAssert().assertTrue(pages().uploadDocumentsTab().creditCardImagesSentMsgExists(), "Credit Card uploaded successfully.");
    }

    /*
     *[TestLink] CTW-5770:Failed upload document: POI section - invalid Passport doc
     */
    @Test(description = "CTW-5770:Failed upload document: POI section - invalid Passport doc")
    @TestLinkProperties(displayId = "CTW-5770")
    public void failedUploadDocumentPOISectionIinvalidPassportDoc () {
        pages().uploadDocumentDialog().poiUploadPassport(WRONG_DOC_PATH);
        softAssert().assertTrue(pages().uploadDocumentDialog().poiXImageExists(), "Red X sign is available");
        softAssert().assertFalse(pages().thankYouPage().startTradeExists(), "Passport has not uploaded and user is not ready to start Trade.");
    }

    /*
     *[TestLink] CTW-5771:Failed upload document: POI section - invalid Driver license doc
     */
    @Test(description = "CTW-5771:Failed upload document: POI section - invalid Driver license doc")
    @TestLinkProperties(displayId = "CTW-5771")
    public void failedUploadDocumentPOISectionInvalidDriverLicenseDoc() {
        pages().uploadDocumentDialog().poiUploadDriverLicense(WRONG_DOC_PATH,WRONG_DOC_PATH);
        softAssert().assertTrue(pages().uploadDocumentDialog().poiXImageExists(), "Red X sign is available");
        softAssert().assertFalse(pages().thankYouPage().startTradeExists(), "Driver license has not uploaded and user is not ready to start Trade.");
    }

    /*
    *[TestLink] CTW-5772:Failed upload document: POI section - invalid Identity card doc
    */
    @Test(description = "CTW-5772:Failed upload document: POI section - invalid Identity card doc")
    @TestLinkProperties(displayId = "CTW-5772")
    public void failedUploadDocumentPOISectionInvalidIdentityCardDoc() {
        pages().uploadDocumentDialog().uploadIdCard(WRONG_DOC_PATH,WRONG_DOC_PATH);
        softAssert().assertTrue(pages().uploadDocumentDialog().poiXImageExists(), "Red X sign is available");
        softAssert().assertFalse(pages().thankYouPage().startTradeExists(), "ID Card has not uploaded and user is ready to start Trade.");
    }

    /*
     * [TestLink] CTW-5773:Failed upload document: POR section
     */
    @Test(description = "CTW-5773:Failed upload document: POR section")
    @TestLinkProperties(displayId = "CTW-5773")
    public void failedUploadDocumentPORSection() {
        pages().uploadDocumentDialog().porUploadElectricityBill(WRONG_DOC_PATH);
        // TODO: This verification is blocked (no error message is available): "a correct red error message is displayed"
        softAssert().assertTrue(pages().uploadDocumentDialog().porXImageExists(), "Failed electricity bill has not uploaded.");
    }

    /*
     *[TestLink] CTW-5774:Failed upload document: Credit Card section
     */
    @Test(description = "CTW-5774:Failed upload document: Credit Card section")
    @TestLinkProperties(displayId = "CTW-5774")
    public void failedUploadDocumentCreditCardSection() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().uploadCreditCard(WRONG_DOC_PATH, WRONG_DOC_PATH);
        // TODO: This verification is blocked (no error message is available): "a correct red error message is displayed"
        softAssert().assertFalse(pages().uploadDocumentsTab().creditCardImagesSentMsgExists(), "The invalid document was Not uploaded.");
        softAssert().assertTrue(pages().uploadDocumentsTab().creditCardXImageExists(), "Red X sign is available");
    }

    public void closeWizardAndGoTrade() {
        pages().uploadDocumentDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().setLeverageDialog().selectLeverage("100");
    }

    public void closeWizardAndGoToMyAccount() {
        closeWizardAndGoTrade();
        pages().topNavigationPage().goToMyAccount();
    }

    public void closeWizardAndGoToUploadDocumentTab() {
        closeWizardAndGoToMyAccount();
        pages().uploadDocumentsTab().invoke();
    }
}

