package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.*;

/**
 * Created by lartemyev on 9/7/17.
 */
public class DepositSlideFunctionalityTest extends  DepositSlideSanityTest {

    final private static String AMOUNT_MAX_PLUS_1_DIGITS = "12345678911";
    final private static String AMOUNT_THE_FIELD_NUST_NOT_EXCEED_10_CHARS_MESSAGE = "The field length must not exceed 10 characters";
    final private static String FAILED_AMOUNT_NON_DIGITS_STRING = "test";
    final private static String AMOUNT_DIGITS_ONLY_ALLOWED_MESSAGE = "Please Enter Only Numbers";
    final private static String FAILED_DEPOSIT_WITH_CHAR = "50cd";
    final private static String FAILED_CC_WITH_CHAR = "CREDITCARD";
    final private static String FAILED_CVV_LONG = "12345";
    final private static String FAILED_CREDIT_CARD_HOLDER_NAME = "Васичка";
    final private static String CITY = "NY123";
    final private static String ZIP_CODE = "zip123";
    final private static String CVV_INVALID_CODE_MESSAGE = "Invalid code, please enter the last 3 digits written on the back of your card";
    final private static String CREDIT_CARD_REGULAR_EXPRESSION  = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

    /*
     *[TestLink] CTW-19425:Amount char limitation
     */
    @Test(description = "CTW-19425:Amount char limitation")
    @TestLinkProperties(displayId = "CTW-19425")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void amountCharLimitation(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withDepositAmount(AMOUNT_MAX_PLUS_1_DIGITS)
                .build());
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getErrorMessageHint(),
                AMOUNT_THE_FIELD_NUST_NOT_EXCEED_10_CHARS_MESSAGE,
                "Verification for '" + AMOUNT_THE_FIELD_NUST_NOT_EXCEED_10_CHARS_MESSAGE + "' tooltip message");
    }

    /*
     *[TestLink] CTW-19429:Digits insertion to amount fiel
     */
    @Test(description = "CTW-19429:Digits insertion to amount field")
    @TestLinkProperties(displayId = "CTW-19429")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void digitsInsertionToAmountField(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withDepositAmount(FAILED_AMOUNT_NON_DIGITS_STRING)
                .build());
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getErrorMessageHint(),
                AMOUNT_DIGITS_ONLY_ALLOWED_MESSAGE,
                "Verification for '" + AMOUNT_DIGITS_ONLY_ALLOWED_MESSAGE + "' tooltip message");
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
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getCreditCardNumber(),
                "",
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
        softAssert().assertTrue(
                pages().creditCardDepositDialog().getCreditCardNumber().matches(CREDIT_CARD_REGULAR_EXPRESSION),
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
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getExpiryDateMonthList().size(),13,
                "Verification expiry month drop down contains 1-12 month");
        pages().creditCardDepositDialog().selectExpiryDateMonth();
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getExpiryDateMonthSelectedItem(),3,
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
        softAssert().assertEquals(
                pages().creditCardDepositDialog().getErrorMessageHint(),
                CVV_INVALID_CODE_MESSAGE,
                "Verification for long CVV code");
    }

    /*
     * [TestLink] CTW-19437:Card holders's name / last name
     */
    @Test(description = "CTW-19437:Card holders's name / last name")
    @TestLinkProperties(displayId = "CTW-19437")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void cardHoldersNameLastName(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCardHoldersFirstName(FAILED_CREDIT_CARD_HOLDER_NAME)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getErrorMessageHint(),"Please Enter Only English characters",
                "Verification for wrong language in Card Holder`s First Name");
        pages().browser().refreshPage();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCardHoldersLastName(FAILED_CREDIT_CARD_HOLDER_NAME)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getErrorMessageHint(),"Please Enter Only English characters",
                "Verification for wrong language in Card Holder`s Last Name");
    }


    /*
     * [TestLink] CTW-19440:City + zip code Text fields
     */
    @Test(description = "CTW-19440:City + zip code Text fields")
    @TestLinkProperties(displayId = "CTW-19440")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void cityAndZipCodeTextFields(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix){
        invokeDepositSlide(countrycode, phonecountryprefix);
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder()
                .withCity(CITY)
                .withZipCode(ZIP_CODE)
                .build());
        softAssert().assertEquals(pages().creditCardDepositDialog().getCreditCardCity(), CITY,
                "Verification for City field is text field");
        softAssert().assertEquals(pages().creditCardDepositDialog().getCreditCardZipCode(), ZIP_CODE,
                "Verification for Zip Code field is text field");
    }

    /*
     * [TestLink] CTW-19446:Country of residence drop down
     */
    @Test(description = "CTW-19446:Country of residence drop down")
    @TestLinkProperties(displayId = "CTW-19446")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void countryOfResidenceDropDown(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        String beforeCountry = pages().creditCardDepositDialog().getSelectedCountryName();
        pages().creditCardDepositDialog().scrollToCountry();
        String afterCountry = pages().creditCardDepositDialog().getSelectedCountryName();
        softAssert().assertNotEquals(beforeCountry,afterCountry,
                "Verification for select country name is scrollable");
    }

    /*
     * [TestLink] CTW-19447 : Submit button Hover
     */
    @Test(description = "CTW-19447:Submit button Hover")
    @TestLinkProperties(displayId = "CTW-19447")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void submitButtonHover(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        invokeDepositSlide(countrycode, phonecountryprefix);
        String beforeColor = pages().creditCardDepositDialog().getSubmitButtonCollor();
        pages().creditCardDepositDialog().moveCursorToSubmitButton();
        String afterColor = pages().creditCardDepositDialog().getSubmitButtonCollor();
        softAssert().assertNotEquals(beforeColor,afterColor, "Verification submit button change background collor");
    }
}