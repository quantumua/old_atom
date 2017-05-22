package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.google.common.base.Strings;

import java.util.Optional;

import static com.betamedia.qe.af.core.utils.StringUtils.generateNumbersSequence;
import static com.betamedia.qe.af.core.utils.StringUtils.generateRandomId;

public class CustomerBuilder {

    public static final int CHARS_IN_ID = 6;
    public static final int CHARS_IN_PHONE_NUMBER = 11;
    public static final String TP_AUTOMATION_PREFIX = "tp_automation_";
    public static final String PASSWORD = "123123";
    public static final String EMAIL_TEMPLATE = "{userName}@automation.ru";
    public static final String DYNAMIC_EMAIL_PART_REGEX = "\\{userName\\}";
    //should be unique
    private String userName;
    private String password = PASSWORD;
    private String firstName = "Automation";
    private String email;
    private String phone;
    private String currency = "EUR";
    private String countryCode = "JM";

    //    every registration opens 2 Trading Accounts (Binary & FX), which the primary is FX/CFD by default.
    //    set target as "binary" to have Binary as a primary TA
    private String target;
    private String lastName = "Automation";
    private String utcOffset;
    private String oftc;
    private String birthOfDate = "1982-02-03";
    private String city;
    private String userAgent;
    private String lang;
    private String phoneTwo;
    private String registrationIp;
    private String stateCode;
    private String street;
    private String sreet2;
    private String title = "Mr";
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
        if (Strings.isNullOrEmpty(userName)) {
            userName = TP_AUTOMATION_PREFIX + generateRandomId(CHARS_IN_ID);
        }
        email = formMail(userName);
        phone = formPhoneNumber();
    }

    private String formMail(String userName) {
        if (!Strings.isNullOrEmpty(email)) {
            return email;
        }
        return EMAIL_TEMPLATE.replaceAll(DYNAMIC_EMAIL_PART_REGEX, userName);
    }

    private String formPhoneNumber(){
        return Optional.ofNullable(phone).orElse(generateNumbersSequence(CHARS_IN_PHONE_NUMBER));
    }

    /**
     * @author Maksym Tsybulskiy Date: 31/03/17.
     */
    public static class CustomerRO {

        private String userName;
        private String password;
        private String firstName;
        private String email;
        private String phone;
        private String currency;
        private String countryCode;

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
        private String target;

        private CustomerRO() {
        }

        private CustomerRO(String userName, String password, String firstName, String email, String phone, String currency, String countryCode) {
            this.userName = userName;
            this.password = password;
            this.firstName = firstName;
            this.email = email;
            this.phone = phone;
            this.currency = currency;
            this.countryCode = countryCode;
        }

        private CustomerRO(String userName, String password, String firstName, String email, String phone, String currency,
                          String countryCode, String lastName, String utcOffset, String oftc, String birthOfDate, String city,
                          String userAgent, String lang, String phoneTwo, String registrationIp, String stateCode, String street,
                          String sreet2, String title, String zip, String channel, String campaign, String kw, String landingpage,
                          String siteid, String p1, String p2, String p3, String p4, String p5, String target) {
            this.userName = userName;
            this.password = password;
            this.firstName = firstName;
            this.email = email;
            this.phone = phone;
            this.currency = currency;
            this.countryCode = countryCode;
            this.lastName = lastName;
            this.utcOffset = utcOffset;
            this.oftc = oftc;
            this.birthOfDate = birthOfDate;
            this.city = city;
            this.userAgent = userAgent;
            this.lang = lang;
            this.phoneTwo = phoneTwo;
            this.registrationIp = registrationIp;
            this.stateCode = stateCode;
            this.street = street;
            this.sreet2 = sreet2;
            this.title = title;
            this.zip = zip;
            this.channel = channel;
            this.campaign = campaign;
            this.kw = kw;
            this.landingpage = landingpage;
            this.siteid = siteid;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.p4 = p4;
            this.p5 = p5;
            this.target = target;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getBirthOfDate() {
            return birthOfDate;
        }

        public void setBirthOfDate(String birthOfDate) {
            this.birthOfDate = birthOfDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getPhoneTwo() {
            return phoneTwo;
        }

        public void setPhoneTwo(String phoneTwo) {
            this.phoneTwo = phoneTwo;
        }

        public String getRegistrationIp() {
            return registrationIp;
        }

        public void setRegistrationIp(String registrationIp) {
            this.registrationIp = registrationIp;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSreet2() {
            return sreet2;
        }

        public void setSreet2(String sreet2) {
            this.sreet2 = sreet2;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getCampaign() {
            return campaign;
        }

        public void setCampaign(String campaign) {
            this.campaign = campaign;
        }

        public String getKw() {
            return kw;
        }

        public void setKw(String kw) {
            this.kw = kw;
        }

        public String getLandingpage() {
            return landingpage;
        }

        public void setLandingpage(String landingpage) {
            this.landingpage = landingpage;
        }

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
            this.siteid = siteid;
        }

        public String getOftc() {
            return oftc;
        }

        public void setOftc(String oftc) {
            this.oftc = oftc;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getP3() {
            return p3;
        }

        public void setP3(String p3) {
            this.p3 = p3;
        }

        public String getP4() {
            return p4;
        }

        public void setP4(String p4) {
            this.p4 = p4;
        }

        public String getP5() {
            return p5;
        }

        public void setP5(String p5) {
            this.p5 = p5;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        /**
         * @return the userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName the userName to set
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "CustomerRO{" +
                    "userName='" + userName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", utcOffset='" + utcOffset + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", oftc='" + oftc + '\'' +
                    ", birthOfDate='" + birthOfDate + '\'' +
                    ", city='" + city + '\'' +
                    ", userAgent='" + userAgent + '\'' +
                    ", currency='" + currency + '\'' +
                    ", lang='" + lang + '\'' +
                    ", phoneTwo='" + phoneTwo + '\'' +
                    ", registrationIp='" + registrationIp + '\'' +
                    ", stateCode='" + stateCode + '\'' +
                    ", street='" + street + '\'' +
                    ", sreet2='" + sreet2 + '\'' +
                    ", title='" + title + '\'' +
                    ", zip='" + zip + '\'' +
                    ", channel='" + channel + '\'' +
                    ", campaign='" + campaign + '\'' +
                    ", kw='" + kw + '\'' +
                    ", landingpage='" + landingpage + '\'' +
                    ", siteid='" + siteid + '\'' +
                    ", p1='" + p1 + '\'' +
                    ", p2='" + p2 + '\'' +
                    ", p3='" + p3 + '\'' +
                    ", p4='" + p4 + '\'' +
                    ", p5='" + p5 + '\'' +
                    ", target='" + target + '\'' +
                    '}';
        }
    }
}