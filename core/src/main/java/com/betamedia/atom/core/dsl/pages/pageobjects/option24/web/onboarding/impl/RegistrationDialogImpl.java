package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.RegistrationDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.stream.Collectors;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;
import static org.testng.Assert.assertTrue;

/**
 * Created by vsnigur on 7/3/17.
 */
public class RegistrationDialogImpl extends AbstractPageObject implements RegistrationDialog, ScriptOperations {

    @StoredId
    private By caption;
    @StoredId
    private By firstName;
    @StoredId
    private By lastName;
    @StoredId
    private By email;
    @StoredId
    private By phonePrefix;
    @StoredId
    private By phoneNumber;
    @StoredId
    private By selectedFlag;
    @StoredId
    private By selectCountry;
    @StoredId
    private By countryWrapper;
    @StoredId
    private By password;
    @StoredId
    private By passwordWrapper;
    @StoredId
    private By retypePassword;
    @StoredId
    private By accountAgree;
    @StoredId
    private By registrationWidget;
    @StoredId
    private By submitButton;
    @StoredId
    private By logo;
    @StoredId
    private By riskWarningFooter;
    @StoredId
    private By liveChat;
    @StoredId
    private By loginButton;
    @StoredId
    private By firstNameInputError;
    @StoredId
    private By lastNameInputError;
    @StoredId
    private By errorMessageHint;
    @StoredId
    private By countryList;
    @StoredId
    private By countryItem;
    @StoredId
    private By countryName;
    @StoredId
    private By countryCode;
    @StoredId
    private By countryNames;
    @StoredId
    private By countryCodes;
    @StoredId
    private By countrySearch;
    @StoredId
    private By countriesList;
    @StoredId
    private By countryComboBox;
    @StoredId
    private By currencyComboBox;
    @StoredId
    private By currencies;
    @StoredId
    private By agreeStatus;
    @StoredId
    private By chatLink;
    @StoredId
    private By phoneField;
    @StoredId
    private By currencyField;
    @StoredId
    private By legallTermsAndConditions;
    @StoredId
    private By bonusTermsConditions;
    @StoredId
    private By cookiePolicy;



    private final String ATTRIBUTE_VALUE = "value";
    private final String ATTRIBUTE_TITLE = "title";
    private final String ATTRIBUTE_HREF = "href";
    private final String ATTRIBUTE_MAX_LENGTH = "maxlength";
    private final String ATTRIBUTE_STYLE_BORDER = "border-bottom-color";
    private final String ATTRIBUTE_TEXT_CONTENT = "textContent";
    private final String SCRIPT_CLICK_FIRST_ELEMENT = "arguments[0].click()";
    private final String PROPERTY_DIRECTION = "direction";

    private static final Logger logger = LogManager.getLogger(RegistrationDialogImpl.class);

    public RegistrationDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String dialogCaption() {
        return waitUntilDisplayed(caption).getText();
    }

    @Override
    public boolean exists() {
        waitUntilDisplayed(caption);
        scrollIntoView(waitUntilDisplayed(submitButton));
        Reporter.log("Wait until displayed register from.<br/>");
        return waitUntilDisplayed(caption,submitButton).isDisplayed();
    }

    @Override
    public boolean logoExists() {
        return waitUntilDisplayed(logo).isDisplayed();
    }

    @Override
    public boolean liveChatExists() {
        return waitUntilDisplayed(liveChat).isDisplayed();
    }

    @Override
    public boolean riskMessageExists() {
        return waitUntilDisplayed(riskWarningFooter).isDisplayed();
    }

    @Override
    public boolean loginButtonExists() {
        return waitUntilDisplayed(loginButton).isDisplayed();
    }

    @Override
    public String getErrorMessageNotification() {
        return waitUntilDisplayed(errorMessageHint).getText();
    }

    @Override
    public String getFirstName() {
        return find(firstName).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String getFirstNameStatusError() {
        String errorText = waitUntilDisplayed(errorMessageHint).getText();
        assertTrue(errorText
                .equalsIgnoreCase(find(firstNameInputError).getAttribute(ATTRIBUTE_TEXT_CONTENT)));
        return errorText;
    }

    @Override
    public String getBorderColorFirstName() {
        return waitUntilDisplayed(firstName).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getLastName() {
        return find(lastName).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String getLastNameStatusError() {
        return waitUntilDisplayed(lastNameInputError).getText();
    }

    @Override
    public String getBorderColorLastName() {
        return waitUntilDisplayed(lastName).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getEmail() {
        return find(email).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String getBorderColorForEmail() {
        return find(email).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public boolean register(CustomerRO customerRO) {
        boolean result = fillRegisterForm(customerRO);
        find(submitButton).click();
        return result;
    }

    @Override
    public void register(CustomerRO customerRO, String countryName) {
        fillRegisterForm(customerRO);
        setCountryPrefix(countryName);
        find(submitButton).click();
    }

    //TODO migrate to using CustomerRegistrationInfo
    @Override
    public boolean fillRegisterForm(CustomerRO customerRO) {
        try {
            waitUntilDisplayed(firstName).clear();
            find(firstName).sendKeys(customerRO.getFirstName());
            find(lastName).clear();
            find(lastName).sendKeys(customerRO.getLastName());
            find(email).clear();
            find(email).sendKeys(customerRO.getEmail());
            find(phoneNumber).clear();
            find(phoneNumber).sendKeys(customerRO.getPhone());
            makeActions().sendKeys(find(passwordWrapper),Keys.ENTER).sendKeys(customerRO.getPassword()).build().perform();
            find(retypePassword).clear();
            find(retypePassword).sendKeys(customerRO.getPassword());
            executeScript(SCRIPT_CLICK_FIRST_ELEMENT, find(accountAgree));
            logger.info(String.format("Fill register form for customer: %s", customerRO.toString()));
            return true;
        } catch (Exception e) {
            Reporter.log("Exception happens during submit new user");
            return false;
        }
    }

    @Override
    public void fillRegisterForm(CustomerRegistrationInfo customerRegistrationInfo) {
        waitUntilDisplayed(firstName).clear();
        find(firstName).sendKeys(customerRegistrationInfo.getFirstName());
        find(lastName).clear();
        find(lastName).sendKeys(customerRegistrationInfo.getLastName());
        find(email).clear();
        find(email).sendKeys(customerRegistrationInfo.getEmail());
        find(phonePrefix).clear();
        find(phonePrefix).sendKeys(customerRegistrationInfo.getPhoneCountryPrefix());
        find(phoneNumber).clear();
        find(phoneNumber).sendKeys(customerRegistrationInfo.getPhoneNumber());
        setPasswordFields(customerRegistrationInfo.getPassword(), customerRegistrationInfo.getPassword());
        clickAgreement();
        logger.info(String.format("Fill register form for customer: %s\n",customerRegistrationInfo.toString()));
        Reporter.log(String.format("Fill register form for customer: %s</br>",customerRegistrationInfo.toString()));
    }

    public void register(CustomerRegistrationInfo customerRegistrationInfo) {
        fillRegisterForm(customerRegistrationInfo);
        submitRegisterForm();
    }

    @Override
    public void setPasswordFields(String password, String confirmPassword) {
        // makeActions().sendKeys(find(passwordWrapper),Keys.ENTER).sendKeys(password).build().perform();
        makeActions().sendKeys(find(passwordWrapper), password).build().perform();
        find(retypePassword).clear();
        find(retypePassword).sendKeys(confirmPassword);
        Reporter.log("Update password fields.<br/>");
    }

    @Override
    public void setCountryPrefix(String country) {
        waitUntilDisplayed(selectedFlag).click();
        scrollIntoView(findElements(countryName)
                        .stream()
                        .filter(element -> element.getText().contains(country))
                        .findFirst().orElse(null)
        ).click();
        Reporter.log(String.format("Select %s country in prefix field.</br>", country));
    }

    @Override
    public String getCountryPrefix() {
        waitUntilDisplayed(phonePrefix).isDisplayed();
        return waitUntilDisplayed(phonePrefix).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String getPhoneNumber() {
        return find(phoneNumber).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String countrySearch(String search,String country) {
        waitUntilDisplayed(countryComboBox);
        waitUntilClickable(countryComboBox).click();
        waitUntilDisplayed(countrySearch).sendKeys(search);
        findElements(countriesList).forEach(el->{if (el.getText().equalsIgnoreCase(country)) scrollIntoView(el).click();});
        Reporter.log(String.format("Select country %s </br>", country));
        return waitUntilDisplayed(countryComboBox).getAttribute(ATTRIBUTE_TITLE);
    }

    @Override
    public void selectCountry(String country) {
        countrySearch("", country);
    }

    @Override
    public void submitRegisterForm() {
        waitUntilDisplayed(submitButton);
        waitUntilClickable(submitButton);
        scrollIntoView(find(submitButton)).click();
        Reporter.log("Submit register form.<br/>");
    }

    @Override
    public List<String> availableCurrencies() {
        waitUntilDisplayed(currencyComboBox).click();
        waitUntilDisplayed(currencies);
        List<String> actualCurrencies = findElements(currencies).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        waitUntilDisplayed(currencyComboBox).click();
        return actualCurrencies;
    }

    @Override
    public String getPasswordSize() {
        return waitUntilDisplayed(retypePassword).getAttribute(ATTRIBUTE_MAX_LENGTH);
    }

    @Override
    public void clickAgreement() {
        executeScript(SCRIPT_CLICK_FIRST_ELEMENT, waitUntilDisplayed(accountAgree));
        Reporter.log("Click agreement checkbox.<br/>");
    }

    @Override
    public String getBorderColorForPassword() {
        return waitUntilDisplayed(retypePassword).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getBorderColorForAgreement() {
        return waitUntilDisplayed(accountAgree).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getBorderForPrefixField() {
        return waitUntilDisplayed(phonePrefix).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getBorderColorPhone() {
        return waitUntilDisplayed(phoneNumber).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String getBorderForCountryField() {
        return waitUntilDisplayed(countryWrapper).getCssValue(ATTRIBUTE_STYLE_BORDER);
    }

    @Override
    public String agreementStatus() {
        return waitUntilDisplayed(agreeStatus).getText();
    }

    @Override
    public boolean submitIsEnabled() {
        return find(submitButton).isEnabled();
    }

    @Override
    public boolean chatLinkDisplayed() {
        try {
            return waitUntilDisplayed(chatLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getCurrency() {
        return find(currencyComboBox).getAttribute(ATTRIBUTE_TITLE);
    }

    @Override
    public void register(String countryName) {
        setCountryPrefix(countryName);
        register(CustomerRO.builder(WebSiteNamingStrategy.get()).build());
    }

    @Override
    public void verifyContentAlignment(Direction expectedDirection) {
        Reporter.log("Make sure all fields have "+expectedDirection+" direction <br/>");
        softAssert().assertEquals(waitUntilDisplayed(caption).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Caption hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(firstName).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "First Name field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(lastName).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Last Name field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(email).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Email field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(phoneField).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Phone prefix field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(phoneNumber).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Phone field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(countryComboBox).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Country hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(currencyField).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Currency field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(passwordWrapper).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Password field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(retypePassword).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Retype password field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(accountAgree).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Account agree field hasn't expected direction");
        softAssert().assertEquals(waitUntilDisplayed(submitButton).getCssValue(PROPERTY_DIRECTION), expectedDirection.value,
                "Submit button hasn't expected direction");
        softAssert().assertAll();
    }

    @Override
    public String getLegallTermsAndConditionsLink() {
        return scrollIntoView(waitUntilDisplayed(legallTermsAndConditions)).getAttribute(ATTRIBUTE_HREF);
    }

    @Override
    public void legallTermsAndConditionsLinkOpen() {
        scrollIntoView(waitUntilDisplayed(legallTermsAndConditions)).click();
    }

    @Override
    public String getBonusTermsConditionsLink() {
        return scrollIntoView(waitUntilDisplayed(bonusTermsConditions)).getAttribute(ATTRIBUTE_HREF);
    }

    @Override
    public void bonusTermsConditionsLinkOpen() {
        scrollIntoView(waitUntilDisplayed(bonusTermsConditions)).click();
    }

    @Override
    public String getCookiePolicyLink() {
        return scrollIntoView(waitUntilDisplayed(cookiePolicy)).getAttribute(ATTRIBUTE_HREF);
    }

    @Override
    public void cookiePolicyLinkOpen() {
        scrollIntoView(waitUntilDisplayed(cookiePolicy)).click();
    }

}
