package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import java.util.List;

/**
 * Created by vsnigur on 7/3/17.
 */
public interface RegistrationDialog {

    String dialogCaption();

    boolean exists();

    boolean logoExists();

    boolean liveChatExists();

    boolean riskMessageExists();

    boolean loginButtonExists();

    String getErrorMessageNotification();

    String getFirstName();

    String getFirstNameStatusError();

    String getBorderColorFirstName();

    String getLastName();

    String getLastNameStatusError();

    String getBorderColorLastName();


    String getEmail();

    String getBorderColorForEmail();

    boolean register(CustomerRO customerRO);

    boolean fillRegisterForm(CustomerRO customerRO);

    void fillRegisterForm(CustomerRegistrationInfo customerRegistrationInfo);

    void setCountryPrefix(String country);

    String getCountryPrefix();

    String getPhoneNumber();

    String countrySearch(String search, String country);

    void submitRegisterForm();

    List<String> availableCurrencies();

    String getPasswordSize();

    void clickAgreement();

    String getBorderColorForPassword();

    String getBorderColorForAgreement();

    String getBorderForPrefixField();

    String getBorderColorPhone();

    String getBorderForCountryField();

    String agreementStatus();

    boolean submitIsEnabled();

    boolean chatLinkDisplayed();

}
