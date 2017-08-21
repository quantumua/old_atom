package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsPersonalInformationImpl;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.*;

/**
 * Created by lartemyev on 8/14/17.
 */
public class DocumentsUploadSlideLocalizationTest extends DocumentsUploadSlideSanityTest {

    private static final Logger logger = LogManager.getLogger(WebFnsPersonalInformationImpl.class);

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        super.before(countrycode, phonecountryprefix);
    }

    /*
     *[TestLink] CTW-5854:AR - Verify when choosing AR language the order of the Upload doc slide is changing RTL
     */
    @Test(description = "CTW-5854:AR - Verify when choosing AR language the order of the Upload doc slide is changing RTL")
    @TestLinkProperties(displayId = "CTW-5854")
    public void arVerifyWhenChoosingARLanguageTheOrderOfTheUploadDocSlideIsChangingRTL() {
        pages().uploadDocumentDialog().verifyDirection(LTR);
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        pages().uploadDocumentDialog().verifyDirection(RTL);
    }

    /*
     *[TestLink] CTW-5856:Upload doc slide Translations - make sure that all languages are translated
     */
    @Test(description = "CTW-5856:Upload doc slide Translations - make sure that all languages are translated")
    @TestLinkProperties(displayId = "CTW-5856")
    public void uploadDocSlideTranslationsMakeSureThatAllLanguagesAreTranslated() {
        for (Language language : Language.values()) {
            pages().topNavigationPage().selectLanguage(language.code);
            pages().uploadDocumentDialog().verifyLocalization(language);
        }
    }
}