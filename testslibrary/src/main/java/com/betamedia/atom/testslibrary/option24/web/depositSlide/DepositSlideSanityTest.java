package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.web.AbstractWebNavigationTest;
import org.testng.annotations.*;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
public class DepositSlideSanityTest extends AbstractWebNavigationTest {

    final private static String DEPOSIT = "500";
    final private static String FAILED_CREDIT_CARD_VISA = "4000027891380962";

    /*
     *[TestLink]CTW-19424:Deposit E2E EU
     */
    @Test(description = "CTW-19424:Deposit E2E EU")
    @TestLinkProperties(displayId = "CTW-19424")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositE2EEU(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        depositE2ETest(countrycode, phonecountryprefix);
    }

    /*
     *[TestLink] CTW-19439:Deposit E2E EUfailure
     */
    @Test(description = "CTW-19439:Deposit E2E EUfailure")
    @TestLinkProperties(displayId = "CTW-19439")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositE2EEUfailure(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        depositE2EFailureTest(countrycode, phonecountryprefix);
    }

    /*
     *[TestLink] CTW-19438:Deposit E2E INT
     */
    @Test(description = "CTW-19438:Deposit E2E INT")
    @TestLinkProperties(displayId = "CTW-19438")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositE2EINT(@Optional("Zambia") String countrycode, @Optional("+260") String phonecountryprefix) {
        depositE2ETest(countrycode, phonecountryprefix);
    }

    /*
     *[TestLink] CTW-19423:Deposit E2E INT failure
     */
    @Test(description = "CTW-19423:Deposit E2E INT failure")
    @TestLinkProperties(displayId = "CTW-19423")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositE2EINTfailure(@Optional("Zambia") String countrycode, @Optional("+260") String phonecountryprefix) {
        depositE2EFailureTest(countrycode, phonecountryprefix);
    }

    // private test methods

    private void depositE2EFailureTest(String countrycode, String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDeposit().submit(CreditCardDeposit.builder()
                .withCreditCardNumber(FAILED_CREDIT_CARD_VISA)
                .build());
        softAssert().assertTrue(
                pages().creditCardDeposit().invalidCreditCardNumberErrorExists(),
                "Verification for Invalid Credit Card Number error message");
    }

    private void depositE2ETest(String countrycode, String phonecountryprefix) {
        CustomerRegistrationInfo customerRegistrationInfo = createUser(countrycode,phonecountryprefix, DEPOSIT);
        closeOnboardingWizardAndGoTrade();
        pages().assets().switchToPanda();
        String accountBalance =pages().cfdPositions().getAccountBalance();
        pages().assets().leavePandaFrame();
        softAssert().assertEquals(
                DEPOSIT,
                accountBalance,
                "Checking set deposit durring onboarding registration and deposit from UI");
        softAssert().assertEquals(
                DEPOSIT,
                operations().customerOperations().getCreditCardDepositExtBaseByEmailAddress(customerRegistrationInfo.getEmail())
                        .getRequestedAmount()
                        .toString(),
                "Checking if deposit created in CRM");
    }
}