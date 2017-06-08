package com.betamedia.atom.core.api.tp.entities.builders;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import org.assertj.core.util.Strings;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.AbstractUserNamingStrategy.DEFAULT_USER_FIRST_NAME;
import static com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.AbstractUserNamingStrategy.DEFAULT_USER_LAST_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.Assert.*;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
public class CustomerROBuilderTest {

    private static final String BIRTH_OF_DATE = "birthOfDate";
    private static final String CAMPAIGN = "campaign";
    private static final String CHANNEL = "channel";
    private static final String CITY = "city";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String CURRENCY = "currency";
    private static final String EMAIL = "email@email.com";
    private static final String FIRST_NAME = "firstName";
    private static final String KW = "kw";
    private static final String LANDING_PAGE = "landingpage";
    private static final String LANG = "lang";
    private static final String LAST_NAME = "lastName";
    private static final String OFTC = "oftc";
    private static final String P1 = "p1";
    private static final String P2 = "p2";
    private static final String P3 = "p3";
    private static final String P4 = "p4";
    private static final String P5 = "p5";
    private static final String PASSWORD = "password";
    private static final String PHONE = "phone";
    private static final String PHONE_TWO = "phoneTwo";
    private static final String REGISTRATION_IP = "registrationIp";
    private static final String SITE_ID = "siteid";
    private static final String SREET2 = "sreet2";
    private static final String STATE_CODE = "stateCode";
    private static final String STREET = "street";
    private static final String TARGET = "target";
    private static final String TITLE = "title";
    private static final String USER_AGENT = "userAgent";
    private static final String USER_NAME = "userName";
    private static final String UTC_OFFSET = "utcOffset";
    private static final String ZIP = "zip";

    private static final String DEFAULT_CURENCY = "EUR";
    private static final String DEFAULT_COUNTRY_CODE = "JM";
    private static final String DEFAULT_PASSWORD = "123123";
    private static final String DEFAULT_EMAIL = "@automation.ru";
    private static final String DEFAULT_USER_NAME = "tp_automation";
    private static final String DEFAULT_TITLE = "Mr";
    private static final String DEFAULT_BIRTH_OF_DATE = "1982-02-03";
    private static final String CUSTOM_PHONE = "12465555555";
    public static final String CRM_EMAIL_NAMING_TEMPLATE = "^crm_(.+)@24options\\.atom$";
    public static final String WEBSITENAMING_TEMPLATE = "^Web(.+)@24options\\.atom$";
    public static final String WIDGET_NAMING_TEMPLATE = "^widget(.+)@24options\\.atom$";

    @Test
    public void testCreateDefaultCustomerRO() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        assertThat(customerRO.getUserName(), is(nullValue()));
        checkMailMatching(CRM_EMAIL_NAMING_TEMPLATE, customerRO.getEmail());
    }

    @Test
    public void shouldGenerateEmailByFirstNameIfNotSet() {
        String customUserName = "customUserName";
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).setUserName(customUserName).build();
        assertTrue(customUserName.equals(customerRO.getUserName()));
        checkMailMatching(CRM_EMAIL_NAMING_TEMPLATE, customerRO.getEmail());
    }


    private void checkMailMatching(String regex, String mail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        assertTrue(matcher.find());
        assertFalse(Strings.isNullOrEmpty(matcher.group(1)));
    }

    @Test
    public void shouldUseSetEmail() {
        String customUserName = "customUserName";
        String email = "dontMatchFirstName@email.com";
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).setUserName(customUserName).setEmail(email).build();
        assertFalse(customerRO.getEmail().contains(customerRO.getUserName()));
    }

    @Test
    public void testDefaultCustomerRO() {
        CustomerRO actualCustomerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        checkDefault(actualCustomerRO, getDefaultCustomerRO());
        checkMailMatching(CRM_EMAIL_NAMING_TEMPLATE, actualCustomerRO.getEmail());
    }

    @Test
    public void testCustomCustomerRO() {
        CustomerRO actualCustomerRO = getCustomCustomerBuilder().build();
        checkCustom(actualCustomerRO, getCustomCustomerRO());
        assertEquals(actualCustomerRO.getEmail(), EMAIL);
        assertTrue(actualCustomerRO.getUserName().contains(USER_NAME));
    }

    private CustomerRO getDefaultCustomerRO() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        customerRO.setCountryCode(DEFAULT_COUNTRY_CODE);
        customerRO.setCurrency(DEFAULT_CURENCY);
        customerRO.setPassword(DEFAULT_PASSWORD);
        customerRO.setPhone(CUSTOM_PHONE);
        customerRO.setEmail(DEFAULT_EMAIL);
        customerRO.setUserName(DEFAULT_USER_NAME);
        customerRO.setFirstName(DEFAULT_USER_FIRST_NAME);
        customerRO.setLastName(DEFAULT_USER_LAST_NAME);
        customerRO.setTitle(DEFAULT_TITLE);
        customerRO.setBirthOfDate(DEFAULT_BIRTH_OF_DATE);
        return customerRO;
    }

    private CustomerRO.CustomerROBuilder getCustomCustomerBuilder() {
        return CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setBirthOfDate(BIRTH_OF_DATE)
                .setCampaign(CAMPAIGN)
                .setChannel(CHANNEL)
                .setCity(CITY)
                .setCountryCode(COUNTRY_CODE)
                .setCurrency(CURRENCY)
                .setEmail(EMAIL)
                .setFirstName(FIRST_NAME)
                .setKw(KW)
                .setLandingpage(LANDING_PAGE)
                .setLang(LANG)
                .setLastName(LAST_NAME)
                .setOftc(OFTC)
                .setP1(P1)
                .setP2(P2)
                .setP3(P3)
                .setP4(P4)
                .setP5(P5)
                .setPassword(PASSWORD)
                .setPhone(PHONE)
                .setPhoneTwo(PHONE_TWO)
                .setRegistrationIp(REGISTRATION_IP)
                .setSiteid(SITE_ID)
                .setStreet2(SREET2)
                .setStateCode(STATE_CODE)
                .setStreet(STREET)
                .setTarget(TARGET)
                .setTitle(TITLE)
                .setUserAgent(USER_AGENT)
                .setUtcOffset(UTC_OFFSET)
                .setZip(ZIP)
                .setUserName(USER_NAME);
    }

    private void checkDefault(CustomerRO actualCustomerRO, CustomerRO expectedCustomerRO) {
        assertEquals(actualCustomerRO.getPhone().length(), CustomerRO.CustomerROBuilder.CHARS_IN_PHONE_NUMBER);
        checkFields(actualCustomerRO, expectedCustomerRO);
    }

    private void checkCustom(CustomerRO actualCustomerRO, CustomerRO expectedCustomerRO) {
        assertEquals(actualCustomerRO.getPhone(), expectedCustomerRO.getPhone());
        checkFields(actualCustomerRO, expectedCustomerRO);
    }

    private void checkFields(CustomerRO actualCustomerRO, CustomerRO expectedCustomerRO) {
        //assertThat(actualCustomerRO, is(samePropertyValuesAs(expectedCustomerRO)));
        //assertThat(defaultCustomer, samePropertyValuesAs(customerRO));
        assertEquals(actualCustomerRO.getBirthOfDate(), expectedCustomerRO.getBirthOfDate());
        assertEquals(actualCustomerRO.getCampaign(), expectedCustomerRO.getCampaign());
        assertEquals(actualCustomerRO.getChannel(), expectedCustomerRO.getChannel());
        assertEquals(actualCustomerRO.getCity(), expectedCustomerRO.getCity());
        assertEquals(actualCustomerRO.getCountryCode(), expectedCustomerRO.getCountryCode());
        assertEquals(actualCustomerRO.getCurrency(), expectedCustomerRO.getCurrency());
        assertEquals(actualCustomerRO.getFirstName(), expectedCustomerRO.getFirstName());
        assertEquals(actualCustomerRO.getKw(), expectedCustomerRO.getKw());
        assertEquals(actualCustomerRO.getLandingpage(), expectedCustomerRO.getLandingpage());
        assertEquals(actualCustomerRO.getLang(), expectedCustomerRO.getLang());
        assertEquals(actualCustomerRO.getLastName(), expectedCustomerRO.getLastName());
        assertEquals(actualCustomerRO.getOftc(), expectedCustomerRO.getOftc());
        assertEquals(actualCustomerRO.getP1(), expectedCustomerRO.getP1());
        assertEquals(actualCustomerRO.getP2(), expectedCustomerRO.getP2());
        assertEquals(actualCustomerRO.getP3(), expectedCustomerRO.getP3());
        assertEquals(actualCustomerRO.getP4(), expectedCustomerRO.getP4());
        assertEquals(actualCustomerRO.getP5(), expectedCustomerRO.getP5());
        assertEquals(actualCustomerRO.getPassword(), expectedCustomerRO.getPassword());
        assertEquals(actualCustomerRO.getPhoneTwo(), expectedCustomerRO.getPhoneTwo());
        assertEquals(actualCustomerRO.getRegistrationIp(), expectedCustomerRO.getRegistrationIp());
        assertEquals(actualCustomerRO.getSiteid(), expectedCustomerRO.getSiteid());
        assertEquals(actualCustomerRO.getSreet2(), expectedCustomerRO.getSreet2());
        assertEquals(actualCustomerRO.getStateCode(), expectedCustomerRO.getStateCode());
        assertEquals(actualCustomerRO.getStreet(), expectedCustomerRO.getStreet());
        assertEquals(actualCustomerRO.getTarget(), expectedCustomerRO.getTarget());
        assertEquals(actualCustomerRO.getTitle(), expectedCustomerRO.getTitle());
        assertEquals(actualCustomerRO.getUserAgent(), expectedCustomerRO.getUserAgent());
        assertEquals(actualCustomerRO.getUtcOffset(), expectedCustomerRO.getUtcOffset());
        assertEquals(actualCustomerRO.getZip(), expectedCustomerRO.getZip());
    }

    private CustomerRO getCustomCustomerRO() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        customerRO.setBirthOfDate(BIRTH_OF_DATE);
        customerRO.setCampaign(CAMPAIGN);
        customerRO.setChannel(CHANNEL);
        customerRO.setCity(CITY);
        customerRO.setCountryCode(COUNTRY_CODE);
        customerRO.setCurrency(CURRENCY);
        customerRO.setEmail(EMAIL);
        customerRO.setFirstName(FIRST_NAME);
        customerRO.setKw(KW);
        customerRO.setLandingpage(LANDING_PAGE);
        customerRO.setLang(LANG);
        customerRO.setLastName(LAST_NAME);
        customerRO.setOftc(OFTC);
        customerRO.setP1(P1);
        customerRO.setP2(P2);
        customerRO.setP3(P3);
        customerRO.setP4(P4);
        customerRO.setP5(P5);
        customerRO.setPassword(PASSWORD);
        customerRO.setPhone(PHONE);
        customerRO.setPhoneTwo(PHONE_TWO);
        customerRO.setRegistrationIp(REGISTRATION_IP);
        customerRO.setSiteid(SITE_ID);
        customerRO.setSreet2(SREET2);
        customerRO.setStateCode(STATE_CODE);
        customerRO.setStreet(STREET);
        customerRO.setTarget(TARGET);
        customerRO.setTitle(TITLE);
        customerRO.setUserAgent(USER_AGENT);
        customerRO.setUserName(USER_NAME);
        customerRO.setUtcOffset(UTC_OFFSET);
        customerRO.setZip(ZIP);
        return customerRO;
    }

    @Test
    public void testCRMMobileApiCreatedCustomerNamingStrategies() {
        CustomerRO crmMobileApiCreatedCustomer = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        checkStrategy(DEFAULT_USER_FIRST_NAME, DEFAULT_USER_LAST_NAME, CRM_EMAIL_NAMING_TEMPLATE, crmMobileApiCreatedCustomer);
    }

    @Test
    public void testWebSiteCreatedCustomerNamingStrategies() {
        CustomerRO webSiteCreatedCustomer = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
        checkStrategy(DEFAULT_USER_FIRST_NAME, DEFAULT_USER_LAST_NAME, WEBSITENAMING_TEMPLATE, webSiteCreatedCustomer);
    }

    @Test
    public void testCRMWidgetsCreatedCustomerNamingStrategies() {
        CustomerRO widgetsCreatedCustomer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        checkStrategy(DEFAULT_USER_FIRST_NAME, DEFAULT_USER_LAST_NAME, WIDGET_NAMING_TEMPLATE, widgetsCreatedCustomer);
    }

    private void checkStrategy(String firstName, String lastName, String emailRegex, CustomerRO customerRO) {
        assertThat(customerRO.getFirstName(), is(firstName));
        assertThat(customerRO.getLastName(), is(lastName));
        checkMailMatching(emailRegex, customerRO.getEmail());
    }
}	
