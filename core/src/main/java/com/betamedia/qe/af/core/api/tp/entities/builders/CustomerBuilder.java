package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.google.common.base.Strings;

import static com.betamedia.qe.af.core.utils.StringUtils.generateRandomId;

public class CustomerBuilder {

    public static final int CHARS_IN_ID = 6;
    public static final String TP_AUTOMATION_PREFIX = "tp_automation_";
    public static final String PASSWORD = "123123";
    public static final String EMAIL_TEMPLATE = "{userName}@automation.ru";
    public static final String DYNAMIC_EMAIL_PART_REGEX = "\\{userName\\}";
    //should be unique
    private String userName;
    private String password = PASSWORD;
    private String firstName = "Automation";
    private String email;
    private String phone = "12465555555";
    private String currency = "USD";
    private String countryCode = "JM";

    //    every registration opens 2 Trading Accounts (Binary & FX), which the primary is FX/CFD by default.
    //    set target as "binary" to have Binary as a primary TA
    private String target = "binary";
    private String lastName;
    private String utcOffset;
    private String oftc;
    private String birthOfDate;
    private String city;
    private String userAgent;
    private String lang;
    private String phoneTwo;
    private String registrationIp;
    private String stateCode;
    private String street;
    private String sreet2;
    private String title;
    private String zip;
    private String channel;
    private String campaign;
    private String kw;
    private String landingpage;
    private String siteid;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;


    public CustomerBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CustomerBuilder setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public CustomerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
        return this;
    }

    public CustomerBuilder setOftc(String oftc) {
        this.oftc = oftc;
        return this;
    }

    public CustomerBuilder setBirthOfDate(String birthOfDate) {
        this.birthOfDate = birthOfDate;
        return this;
    }

    public CustomerBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public CustomerBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public CustomerBuilder setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public CustomerBuilder setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
        return this;
    }

    public CustomerBuilder setRegistrationIp(String registrationIp) {
        this.registrationIp = registrationIp;
        return this;
    }

    public CustomerBuilder setStateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public CustomerBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public CustomerBuilder setSreet2(String sreet2) {
        this.sreet2 = sreet2;
        return this;
    }

    public CustomerBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomerBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public CustomerBuilder setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public CustomerBuilder setCampaign(String campaign) {
        this.campaign = campaign;
        return this;
    }

    public CustomerBuilder setKw(String kw) {
        this.kw = kw;
        return this;
    }

    public CustomerBuilder setLandingpage(String landingpage) {
        this.landingpage = landingpage;
        return this;
    }

    public CustomerBuilder setSiteid(String siteid) {
        this.siteid = siteid;
        return this;
    }

    public CustomerBuilder setP1(String p1) {
        this.p1 = p1;
        return this;
    }

    public CustomerBuilder setP2(String p2) {
        this.p2 = p2;
        return this;
    }

    public CustomerBuilder setP3(String p3) {
        this.p3 = p3;
        return this;
    }

    public CustomerBuilder setP4(String p4) {
        this.p4 = p4;
        return this;
    }

    public CustomerBuilder setP5(String p5) {
        this.p5 = p5;
        return this;
    }

    public CustomerBuilder setTarget(String target) {
        this.target = target;
        return this;
    }

    public CustomerRO createCustomerRO() {
        formDefaultUniqueFields();
        return new CustomerRO(userName, password, firstName, email, phone, currency, countryCode, lastName, utcOffset, oftc, birthOfDate, city, userAgent, lang, phoneTwo, registrationIp, stateCode, street, sreet2, title, zip, channel, campaign, kw, landingpage, siteid, p1, p2, p3, p4, p5, target);
    }

    private void formDefaultUniqueFields() {
        if (!Strings.isNullOrEmpty(userName)) {
            email = formMail(userName);
        } else {
            String uniqueId = generateRandomId(CHARS_IN_ID);
            userName = TP_AUTOMATION_PREFIX + generateRandomId(CHARS_IN_ID);
            email = formMail(userName);
        }
    }

    private String formMail(String userName) {
        if (!Strings.isNullOrEmpty(email)) {
            return email;
        }
        return EMAIL_TEMPLATE.replaceAll(DYNAMIC_EMAIL_PART_REGEX, userName);
    }
}