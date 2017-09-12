package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.LTR;
import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.RTL;

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
        pages().creditCardDepositDialog().verifyLocalization(Language.RUSSIAN, LocalizationOperations::getTextOrPlaceholder);
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
        pages().creditCardDepositDialog().verifyLocalization(Language.GERMAN, LocalizationOperations::getTextOrPlaceholder);
    }

    /*
    *[TestLink]CTW-19443:Slide on AR
    */
    @Test(description = "CTW-19443:Slide on AR")
    @TestLinkProperties(displayId = "CTW-19443")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositSlideOnArabic(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        createUserWithoutSubmitedDeposit(countrycode,phonecountryprefix);
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        pages().creditCardDepositDialog().verifyLocalization(Language.ARABIC,LocalizationOperations::getTextOrPlaceholder);
        pages().creditCardDepositDialog().verifyDirection(RTL);
    }
}
