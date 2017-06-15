package com.betamedia.atom.core.api.tp.entities.request;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.UserNamingStrategy;
import com.betamedia.atom.core.utils.StringUtils;
import org.testng.Reporter;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Maksym Tsybulskiy Date: 31/03/17.
 */
public class CustomerRO {

    //when the userName is not set CRMMobileApi create the new user with email as userName
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

    public String getBirthdayYear() {
        LocalDate dateOfBirth = LocalDate.parse(getBirthOfDate());
        return Integer.toString(dateOfBirth.getYear());
    }

    public String getBirthdayMonth() {
        LocalDate dateOfBirth = LocalDate.parse(getBirthOfDate());
        return Integer.toString(dateOfBirth.getMonthValue());
    }

    public String getBirthdayDayOfMonth() {
        LocalDate dateOfBirth = LocalDate.parse(getBirthOfDate());
        return Integer.toString(dateOfBirth.getDayOfMonth());
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
                ", street2='" + sreet2 + '\'' +
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

    public static CustomerROBuilder builder(UserNamingStrategy namingStrategy) {
        return new CustomerROBuilder(namingStrategy);
    }

    public static class CustomerROBuilder {

        public static final int CHARS_IN_PHONE_NUMBER = 11;
        public static final String TP_AUTOMATION_PREFIX = "tp_automation_";
        public static final String DEFAULT_PASSWORD = "123123";
        public static final String DEFAULT_COUNTRY = "JM";
        public static final String DEFAULT_CURRENCY = "EUR";
        public static final String DEFAULT_BIRTHOFDATE = "1982-02-03";
        public static final String DEFAULT_TITLE = "Mr";
        
        //should be unique
        private String userName;
        private String password = DEFAULT_PASSWORD;
        private String firstName;
        private String email;
        private String phone;
        private String currency = DEFAULT_CURRENCY; 
        private String countryCode = DEFAULT_COUNTRY;

        //    every registration opens 2 Trading Accounts (Binary & FX), which the primary is FX/CFD by default.
        //    set target as "binary" to have Binary as a primary TA
        private String target;
        private String lastName;
        private String utcOffset;
        private String oftc;
        private String birthOfDate = DEFAULT_BIRTHOFDATE;
        private String city;
        private String userAgent;
        private String lang;
        private String phoneTwo;
        private String registrationIp;
        private String stateCode;
        private String street;
        private String street2;
        private String title = DEFAULT_TITLE;
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

        private UserNamingStrategy userNamingStrategy;

        private CustomerROBuilder(UserNamingStrategy userNamingStrategy) {
            this.userNamingStrategy = userNamingStrategy;
        }

        public CustomerROBuilder setUserNamingStrategy(UserNamingStrategy userNamingStrategy) {
            this.userNamingStrategy = userNamingStrategy;
            return this;
        }

        public CustomerROBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public CustomerROBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerROBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerROBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerROBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerROBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public CustomerROBuilder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public CustomerROBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerROBuilder setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
            return this;
        }

        public CustomerROBuilder setOftc(String oftc) {
            this.oftc = oftc;
            return this;
        }

        public CustomerROBuilder setBirthOfDate(String birthOfDate) {
            this.birthOfDate = birthOfDate;
            return this;
        }

        public CustomerROBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public CustomerROBuilder setUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public CustomerROBuilder setLang(String lang) {
            this.lang = lang;
            return this;
        }

        public CustomerROBuilder setPhoneTwo(String phoneTwo) {
            this.phoneTwo = phoneTwo;
            return this;
        }

        public CustomerROBuilder setRegistrationIp(String registrationIp) {
            this.registrationIp = registrationIp;
            return this;
        }

        public CustomerROBuilder setStateCode(String stateCode) {
            this.stateCode = stateCode;
            return this;
        }

        public CustomerROBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public CustomerROBuilder setStreet2(String street2) {
            this.street2 = street2;
            return this;
        }

        public CustomerROBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public CustomerROBuilder setZip(String zip) {
            this.zip = zip;
            return this;
        }

        public CustomerROBuilder setChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public CustomerROBuilder setCampaign(String campaign) {
            this.campaign = campaign;
            return this;
        }

        public CustomerROBuilder setKw(String kw) {
            this.kw = kw;
            return this;
        }

        public CustomerROBuilder setLandingpage(String landingpage) {
            this.landingpage = landingpage;
            return this;
        }

        public CustomerROBuilder setSiteid(String siteid) {
            this.siteid = siteid;
            return this;
        }

        public CustomerROBuilder setP1(String p1) {
            this.p1 = p1;
            return this;
        }

        public CustomerROBuilder setP2(String p2) {
            this.p2 = p2;
            return this;
        }

        public CustomerROBuilder setP3(String p3) {
            this.p3 = p3;
            return this;
        }

        public CustomerROBuilder setP4(String p4) {
            this.p4 = p4;
            return this;
        }

        public CustomerROBuilder setP5(String p5) {
            this.p5 = p5;
            return this;
        }

        public CustomerROBuilder setTarget(String target) {
            this.target = target;
            return this;
        }

        public CustomerRO build() {
            firstName = userNamingStrategy.getFirstName(firstName);
            lastName = userNamingStrategy.getLastName(lastName);
            email = userNamingStrategy.getEmail(email);
            phone = formPhoneNumber(phone);
            CustomerRO customerRO = new CustomerRO(userName, password, firstName, email, phone, currency, countryCode, lastName, utcOffset, oftc, birthOfDate, city, userAgent, lang, phoneTwo, registrationIp, stateCode, street, street2, title, zip, channel, campaign, kw, landingpage, siteid, p1, p2, p3, p4, p5, target);
            addUserDetailsToReport(customerRO);
            return customerRO;
        }

        private String formPhoneNumber(String phone) {
            return Optional.ofNullable(phone).orElse(StringUtils.generateNumbersSequence(CHARS_IN_PHONE_NUMBER));
        }

        private void addUserDetailsToReport(CustomerRO customerRO) {
            Reporter.log("Customer details:" + customerRO.toString() + '\n');
        }
    }
}
