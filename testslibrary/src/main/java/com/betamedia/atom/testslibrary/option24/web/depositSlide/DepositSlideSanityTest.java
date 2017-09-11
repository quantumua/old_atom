package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.web.AbstractWebNavigationTest;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */

public class DepositSlideSanityTest extends AbstractWebNavigationTest {

    final private static String DEPOSIT = "500";
    final private static String FAILED_DEPOSIT = "100000000000";
    final private static String FAILED_DEPOSIT_WITH_CHAR = "50cd";
    final private static String FAILED_CC_WITH_CHAR = "CREDITCARD";
    final private static String FAILED_CREDIT_CARD_VISA = "4000027891380962";
    final private static String FAILED_CVV_LONG = "12345";
    final private static String CREDIT_CARD_REGULAR_EXPRESSION  = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";


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

    /*
     *[TestLink] CTW-19425:Amount char limitation
     */
    @Test(description = "CTW-19425:Amount char limitation")
    @TestLinkProperties(displayId = "CTW-19425")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void amountCharLimitation(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withDepositAmount(FAILED_DEPOSIT)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getErrorMessageHint(),"The field length must not exceed 10 characters",
                "Verification for tooltip loads explaining that amount cannot be bigger than 10 chars");
    }

    /*
     *[TestLink] CTW-19429:Digits insertion to amount field
     */
    @Test(description = "CTW-19429:Digits insertion to amount field")
    @TestLinkProperties(displayId = "CTW-19429")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void digitsInsertionToAmountField(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withDepositAmount(FAILED_DEPOSIT_WITH_CHAR)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getErrorMessageHint(),"Please Enter Only Numbers",
                "Verification for tooltip loads explaining the possibility insert only digits");
    }

    /*
     * [TestLink] CTW-19430:CC number input type
     */
    @Test(description = "CTW-19430:CC number input type")
    @TestLinkProperties(displayId = "CTW-19430")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void ccNumberInputType(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCreditCardNumber(FAILED_CC_WITH_CHAR)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getCreditCardNumber(),"",
                "Verification that user can`t input in CC field symbol different than numbers");
    }

    /*
     * [TestLink] CTW-19432:CC field seperators
     */
    @Test(description = "CTW-19432:CC field seperators")
    @TestLinkProperties(displayId = "CTW-19432")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void ccFieldSeparators(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withDepositAmount(FAILED_DEPOSIT_WITH_CHAR)
                .build());
        softAssert().assertTrue(pages().creditCardDepositDialog().getCreditCardNumber().matches(CREDIT_CARD_REGULAR_EXPRESSION),
                "Verification for CC field seperators");
    }


    /*
     * [TestLink] CTW-19434:Month Drop down
     */
    @Test(description = "CTW-19434:Month Drop down")
    @TestLinkProperties(displayId = "CTW-19434")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void monthDropDown(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        softAssert().assertEquals(pages().creditCardDepositDialog().getExpiryDateMonthList().size(),13,
                "Verification expiry month drop down contains 1-12 month");
        pages().creditCardDepositDialog().selectExpiryDateMonth();
        softAssert().assertEquals(pages().creditCardDepositDialog().getExpiryDateMonthSelectedItem(),3,
                "Verification expiry month drop down is clicable");
    }

    /*
     * [TestLink] CTW-19435:Year Drop down
     */
    @Test(description = "CTW-19435:Year Drop down")
    @TestLinkProperties(displayId = "CTW-19435")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void yearDropDown(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        softAssert().assertEquals(pages().creditCardDepositDialog().getExpiryDateYearList().size(),16,
                "Verification expiry years drop down contains data");
        pages().creditCardDepositDialog().selectExpiryDateYear();
        softAssert().assertEquals(pages().creditCardDepositDialog().getExpiryDateYearSelectedItem(),2019,
                "Verification expiry year drop down is clicable");
    }

    /*
     * [TestLink] CTW-19436:CVV digits
     */
    @Test(description = "CTW-19436:CVV digits")
    @TestLinkProperties(displayId = "CTW-19436")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void cvvDigits(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCVV2(FAILED_CVV_LONG)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getErrorMessageHint(),"Invalid code, please enter the last 3 digits written on the back of your card",
                "Verification for long CVV code");
    }


    // private test methods

    private void depositE2EFailureTest(String countrycode, String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCreditCardNumber(FAILED_CREDIT_CARD_VISA)
                .build());
        softAssert().assertTrue(
                pages().creditCardDepositDialog().invalidCreditCardNumberErrorExists(),
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