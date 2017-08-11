package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsPersonalInformationImpl;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Leonid Artemiev
 * @since 8/10/17
 */
public class DocumentsUploadSlideFunctionalityTest extends DocumentsUploadSlideSanityTest {

    /**
     * Strings for tests
     */

    protected static final Integer POI_OCR_STATUS_EMPTY = 100000001;
    protected static final Integer POI_OCR_STATUS_NOT_VERIFIED = 100000002;
    protected static final Integer POI_OCR_STATUS_VERIFIED = 100000003;


    private static final Logger logger = LogManager.getLogger(WebFnsPersonalInformationImpl.class);

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
          super.before(countrycode, phonecountryprefix);
    }

    /*
     *[TestLink] CTW-5691:SEU: Upload Documents slide contains Exit (X) button test
     */
    @Test(description = "CTW-5691:SEU: Upload Documents slide contains Exit (X) button test")
    @TestLinkProperties(displayId = "CTW-5691")
    public void uploadDocumentsSlideContainsExitXButtonTest() {
        softAssert().assertTrue(pages().uploadDocumentDialog().isExitButtonExists(), "Upload Documents slide contain an Exit (X) button.");
        pages().uploadDocumentDialog().close();
        softAssert().assertTrue(pages().confirmCloseMessage().exists(), "Exit (X) button is working correct. Expected message is present.");
        pages().confirmCloseMessage().dismissClose();
        softAssert().assertFalse(pages().confirmCloseMessage().exists(), "Pop up message closed. (No) button is working correct.");
        pages().uploadDocumentDialog().close();
        pages().confirmCloseMessage().acceptClose();
        softAssert().assertTrue(pages().topNavigationPage().isLoggedIn(), "Pop up message closed. (Yes) button is working correct. User remained on the Trading platform.");
    }

    /*
     *[TestLink] CTW-5730:SEU: Each slide section can be collapsed / expanded
     */
    @Test(description = "CTW-5730:SEU: Each slide section can be collapsed / expanded")
    @TestLinkProperties(displayId = "CTW-5730")
    public void eachSlideSectionCanBeCollapsedExpanded() {
        pages().uploadDocumentDialog().poiClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPoiExpanded(), "POI section not expanded.");
        pages().uploadDocumentDialog().poiClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPoiCollapsed(), "POI section not collapsed.");
        pages().uploadDocumentDialog().porClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPorExpanded(), "POR section not expanded.");
        pages().uploadDocumentDialog().porClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPorCollapsed(), "POR section not collapsed.");
    }

    /*
     *[TestLink] CTW-5351:POI - Upload passport - SC
     */
    @Test(description = "CTW-5351:POI - Upload passport - SC")
    @TestLinkProperties(displayId = "CTW-5351")
    public void poiUploadPassportSC() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().poiUploadPassport(POI_PASSPORT_PATH);
        // Document status changed to Sent
        pages().uploadDocumentsTab().verifyPOIDocumentIsUploaded();
        softAssert().assertFalse(pages().uploadDocumentDialog().poiBackImageExists(), "There is no prompt to upload Back side of the document");
        // - Verify in CRM POI OCR Status = Verified
        verifyPOIStatusInCRM(POI_OCR_STATUS_VERIFIED);
    }

    /*
     *[TestLink] CTW-5354:Upload Identity Card
     */
    @Test(description = "CTW-5354:Upload Identity Card")
    @TestLinkProperties(displayId = "CTW-5354")
    public void uploadIdentityCard() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().poiUploadIdCardDocuments(POI_ID_FRONT_PATH, POI_ID_BACK_PATH);
        pages().uploadDocumentsTab().verifyPOIDocumentsUploaded(2);
    }

    /*
     *[TestLink] CTW-5355:Upload Driver License
     */
    @Test(description = "CTW-5355:Upload Driver License")
    @TestLinkProperties(displayId = "CTW-5355")
    public void uploadDriverLicense() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().poiUploadDriverLicenseDocuments(POI_DRIVER_LICENSE_FRONT_PATH, POI_DRIVER_LICENSE_BACK_PATH);
        pages().uploadDocumentsTab().verifyPOIDocumentsUploaded(2);
    }

    /*
     *[TestLink] CTW-5356:POI - Upload invalid passport _SC
     */
    @Test(description = "CTW-5356:POI - Upload invalid passport _SC")
    @TestLinkProperties(displayId = "CTW-5356")
    public void poiUploadInvalidPassportSC() {
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().poiUploadPassport(WRONG_DOC_PATH);
        pages().uploadDocumentsTab().verifyPOIInvalidDocumentUploaded(1);
        verifyPOIStatusInCRM(POI_OCR_STATUS_EMPTY);
    }

    private void verifyPOIStatusInCRM (Integer poiOCRStatus) {
        pages().accountDetails().invoke();
        String emailAddress = pages().accountDetails().getEmail();
        final ContactExtension contactExtension = operations().customerOperations().findExtByEmailAddress(emailAddress);
//        logger.info("contactExtension.getPOIStatus()=" + contactExtension.getPOIStatus());
//        Reporter.log("contactExtension.getPOIStatus()=" + contactExtension.getPOIStatus());
        logger.info("contactExtension.getPOIOcrStatus()=" + contactExtension.getPOIOcrStatus());
        Reporter.log("contactExtension.getPOIOcrStatus()=" + contactExtension.getPOIOcrStatus());
//        softAssert().assertEquals(contactExtension.getPOIStatus(), poiStatus, "Failed to verify POI status, actual status: " + contactExtension.getPOIStatus());
        softAssert().assertEquals(contactExtension.getPOIOcrStatus(), poiOCRStatus, "Failed to verify POI Ocr status, actual status: " + contactExtension.getPOIOcrStatus());
    }

    //        pages().topNavigationPage().logIn();
    //        String TEMP_EMAIL= "Web_g8jj8@24options.atom";
    //        pages().loginDialog().login(TEMP_EMAIL, "123123");
    //        String emailAddress = TEMP_EMAIL;
}
