package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vsnigur on 7/3/17.
 */
public class RegistrationDialogImpl extends AbstractPageObject implements RegistrationDialog {

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
    private By languageMenu;
    @StoredId
    private By riskWarningFooter;
    @StoredId
    private By liveChat;
    @StoredId
    private By loginButton;
    @StoredId
    private By firstNameInputError;
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

    private final String ATTRIBUTE_VALUE = "value";
    private final String ATTRIBUTE_TITLE = "title";
    private final String ATTRIBUTE_MAX_LENGTH = "maxlength";
    private final String ATTRIBUTE_STYLE_BORDER = "border-bottom-color";
    private final String SCRIPT_CLICK_FIRST_ELEMENT = "arguments[0].click()";

    public RegistrationDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String dialogCaption() {
        return waitUntilDisplayed(caption).getText();
    }

    @Override
    public boolean exists() {
        scrollIntoView(waitUntilDisplayed(submitButton));
        return waitUntilDisplayed(caption,submitButton).isDisplayed();
    }

    @Override
    public boolean logoExists() {
        return waitUntilDisplayed(logo).isDisplayed();
    }

    @Override
    public boolean languageExists() {
        return waitUntilDisplayed(languageMenu).isDisplayed();
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
    public String getLastName() {
        return find(lastName).getAttribute(ATTRIBUTE_VALUE);
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
            return true;
        } catch (Exception e) {
            Reporter.log("Exception happens during submit new user");
            return false;
        }
    }

    private void clearPasswordField() {
        for(int k=0;k<=15;) makeActions().sendKeys(find(passwordWrapper),Keys.BACK_SPACE);
    }

    @Override
    public void setCountryPrefix(String country) {
        waitUntilDisplayed(selectedFlag).click();
        scrollIntoView(findElements(countryName)
                        .stream()
                        .filter(element -> element.getText().contains(country))
                        .findFirst().orElse(null)
        ).click();
    }

    @Override
    public String getCountryPrefix() {
        System.out.print(waitUntilDisplayed(phonePrefix).isDisplayed());
        return waitUntilDisplayed(phonePrefix).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String getPhoneNumber() {
        return find(phoneNumber).getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public String countrySearch(String search,String country) {
        waitUntilDisplayed(countryComboBox).click();
        find(countrySearch).sendKeys(search);
        findElements(countriesList).forEach(el->{if (el.getText().equalsIgnoreCase(country)) scrollIntoView(el).click();});
        return find(countryComboBox).getAttribute(ATTRIBUTE_TITLE);
    }

    @Override
    public void submitRegisterForm() {
        waitUntilDisplayed(submitButton).click();
    }

    @Override
    public List<String> availableCurrencies() {
        waitUntilDisplayed(currencyComboBox).click();
        return findElements(currencies).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Override
    public String getPasswordSize() {
        return waitUntilDisplayed(retypePassword).getAttribute(ATTRIBUTE_MAX_LENGTH);
    }

    @Override
    public void clickAgreement() {
        executeScript(SCRIPT_CLICK_FIRST_ELEMENT, waitUntilDisplayed(accountAgree));
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

}
