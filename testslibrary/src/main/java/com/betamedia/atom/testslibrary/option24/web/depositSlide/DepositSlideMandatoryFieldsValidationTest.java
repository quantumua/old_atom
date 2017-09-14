package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.*;

/**
 * Created by lartemyev on 9/13/17.
 */

public class DepositSlideMandatoryFieldsValidationTest extends  DepositSlideSanityTest{

    /*
     * [TestLink] CTW-19444:Tooltips on Hover
     */
    @Test(description = "CTW-19444:Tooltips on Hover")
    @TestLinkProperties(displayId = "CTW-19444")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void tooltipsOnHover(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().assertToolTipVisibilyty();
    }

    /*
  * [TestLink] CTW-19445:Empty form test
  */
    @Test(description = "CTW-19445:Empty form test")
    @TestLinkProperties(displayId = "CTW-19445")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void emptyFormTest(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().assertEmptyFieldTooltipError(CreditCardDeposit.builder().build());
        softAssert().assertTrue(pages().thankYouPage().thankYouMessageExists(), "Verification for success submitting deposit slide");
    }
}
