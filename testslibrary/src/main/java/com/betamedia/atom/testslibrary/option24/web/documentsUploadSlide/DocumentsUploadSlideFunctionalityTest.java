package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsPersonalInformationImpl;
import com.betamedia.atom.core.persistence.entities.ContactBase;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author Leonid Artemiev
 * @since 8/10/17
 */
public class DocumentsUploadSlideFunctionalityTest extends DocumentsUploadSlideSanityTest {

    /**
     * Strings for tests
     */
    protected static final Integer POI_STATUS_SUCCESS = 100000001;
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
        pages().topNavigationPage().signUp();
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
//        pages().topNavigationPage().logIn();
//        pages().loginDialog().login("Web_69s4h9@24options.atom", "123123");
        closeWizardAndGoToUploadDocumentTab();
        pages().uploadDocumentsTab().poiUploadPassport(POI_PASSPORT_PATH);
        // Document status changed to Sent
        pages().uploadDocumentsTab().verifyPOIDocumentIsUploaded();
        softAssert().assertFalse(pages().uploadDocumentDialog().poiBackImageExists(), "There is no prompt to upload Back side of the document");
        // - Verify in CRM POI OCR Status = Verified
        // TODO: Implement getting email address from account details in UI
        final ContactBase customer=operations().customerOperations().findByEmailAddress("Web_o0jv6q@24options.atom");
        String customerID = customer.getContactId();
        final ContactExtension contactExtension = operations().customerOperations().getContactExtension(customerID);
        softAssert().assertEquals(contactExtension.getPOIStatus(), POI_STATUS_SUCCESS);
        softAssert().assertEquals(contactExtension.getPOIOcrStatus(), POI_OCR_STATUS_VERIFIED);
    }
}
