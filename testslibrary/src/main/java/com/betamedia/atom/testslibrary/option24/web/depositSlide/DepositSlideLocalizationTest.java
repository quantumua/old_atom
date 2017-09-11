package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.web.AbstractWebNavigationTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Leonid Artemiev
 * @since 9/08/17
 */

public class DepositSlideLocalizationTest extends DepositSlideSanityTest {

    /*
     *[TestLink]CTW-19441:Slide on RU
     */
    @Test(description = "CTW-19441:Slide on RU")
    @TestLinkProperties(displayId = "CTW-19441")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositSlideOnRussian(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserWithoutSubmitedDeposit(countrycode,phonecountryprefix);
        pages().topNavigationPage().selectLanguage(Language.RUSSIAN.code);
        pages().creditCardDepositDialog().verifyLocalization(Language.RUSSIAN);
        pages().topNavigationPage().selectLanguage(Language.DUTCH.code);
        pages().creditCardDepositDialog().verifyLocalization(Language.DUTCH);
    }

    /*
     *[TestLink]CTW-19442:Slide on DE
     */
    @Test(description = "CTW-19442:Slide on DE")
    @TestLinkProperties(displayId = "CTW-19442")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositSlideOnGerman(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserWithoutSubmitedDeposit(countrycode,phonecountryprefix);
        pages().topNavigationPage().selectLanguage(Language.GERMAN.code);
        pages().creditCardDepositDialog().verifyLocalization(Language.GERMAN);
    }
}
