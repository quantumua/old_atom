package com.betamedia.atom.core.environment.tp.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
public abstract class AbstractEnvPropertiesHolder<T extends EnvironmentDependent> implements EnvironmentDependent, SpacePropertiesHolder<T>, EntityPropertiesHolder<T>,
        FeedGatewayPropertiesHolder<T>, CRMPropertiesHolder<T>, PersistenceProperties<T> {

    public static final String DOT = ".";
    public static final String TP_SPACE_LOCATORS = "tp.spaceLocators";
    public static final String TP_SPACE_USERNAME = "tp.spaceUsername";
    public static final String TP_SPACE_PASSWORD = "tp.spacePassword";
    public static final String TP_SPACE_GROUPS = "tp.spaceGroups";
    public static final String TP_SPACE_URL = "tp.spaceUrl";
    public static final String TP_USERNAME = "tp.username";
    public static final String TP_PWD = "tp.pwd";

    public static final String GW_SPACE_LOCATORS = "gw.spaceLocators";
    public static final String GW_SPACE_USERNAME = "gw.spaceUsername";
    public static final String GW_SPACE_PASSWORD = "gw.spacePassword";
    public static final String GW_SPACE_GROUPS = "gw.spaceGroups";
    public static final String GW_SPACE_URL = "gw.spaceUrl";

    public static final String TP_DEFAULT_ACCOUNTGROUP_ID = "tp.accountgroup.id";
    public static final String BRAND_ID = "tp.brandId";
    public static final String MOBILE_CRM_URL = "mobile.crm.url";
    public static final String CRM_URL = "crm.url";
    public static final String BACKOFFICE_USERNAME = "bo.username";
    public static final String BACKOFFICE_PASSWORD = "bo.password";
    public static final String BACKOFFICE_VERSION_URL = "bo.version.url";

    public static final String DRIVER_CLASS_NAME = "db.driver-class-name";
    public static final String DATABASE_URL = "db.url";
    public static final String DATABASE_USERNAME = "db.username";
    public static final String DATABASE_PASSWORD = "db.password";

    @Autowired
    private Environment env;

    //    GS connection properties
    private String spaceLocators;
    private String spaceUsername;
    private String spacePassword;
    private String spaceGroups;
    private String spaceURL;
    private String username;
    private String pwd;

    //    FeedGateway connection properties
    private String gwSpaceLocators;
    private String gwSpaceUsername;
    private String gwSpacePassword;
    private String gwSpaceGroups;
    private String gwSpaceURL;

    private String accountGroup;
    private String brandId;
    private String mobileCRMUrl;
    private String crmUrl;
    private String backOfficeUsername;
    private String backOfficePassword;
    private String backOfficeVersionUrl;

    private String driverClassName;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    @PostConstruct
    public void init() {
        spaceLocators = getProperty(TP_SPACE_LOCATORS);
        spaceUsername = getProperty(TP_SPACE_USERNAME);
        spacePassword = getProperty(TP_SPACE_PASSWORD);
        spaceGroups = getProperty(TP_SPACE_GROUPS);
        spaceURL = getProperty(TP_SPACE_URL);
        username = getProperty(TP_USERNAME);
        pwd = getProperty(TP_PWD);

        gwSpaceLocators = getProperty(GW_SPACE_LOCATORS);
        gwSpaceUsername = getProperty(GW_SPACE_USERNAME);
        gwSpacePassword = getProperty(GW_SPACE_PASSWORD);
        gwSpaceGroups = getProperty(GW_SPACE_GROUPS);
        gwSpaceURL = getProperty(GW_SPACE_URL);

        accountGroup = getProperty(TP_DEFAULT_ACCOUNTGROUP_ID);
        brandId = getProperty(BRAND_ID);
        mobileCRMUrl = getProperty(MOBILE_CRM_URL);
        crmUrl = getProperty(CRM_URL);
        backOfficeUsername = getProperty(BACKOFFICE_USERNAME);
        backOfficePassword = getProperty(BACKOFFICE_PASSWORD);
        backOfficeVersionUrl = getOptionalProperty(BACKOFFICE_VERSION_URL);

        driverClassName = getProperty(DRIVER_CLASS_NAME);
        databaseUrl = getProperty(DATABASE_URL);
        databaseUsername = getProperty(DATABASE_USERNAME);
        databasePassword = getProperty(DATABASE_PASSWORD);
    }

    private String getProperty(String postfix) {
        return env.getRequiredProperty(getKeyPrefix() + DOT + postfix);
    }

    private String getOptionalProperty(String postfix){
        return env.getProperty(getKeyPrefix() + DOT + postfix);
    }

    private String getKeyPrefix() {
        return getEnvironment().getValue();
    }

    @Override
    public String getSpaceLocators() {
        return spaceLocators;
    }

    @Override
    public String getSpaceUsername() {
        return spaceUsername;
    }

    @Override
    public String getSpacePassword() {
        return spacePassword;
    }

    @Override
    public String getSpaceGroups() {
        return spaceGroups;
    }

    @Override
    public String getSpaceURL() {
        return spaceURL;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public String getDefaultAccountGroupId() {
        return accountGroup;
    }

    @Override
    public String getGWSpaceLocators() {
        return gwSpaceLocators;
    }

    @Override
    public String getGWSpaceUsername() {
        return gwSpaceUsername;
    }

    @Override
    public String getGWSpacePassword() {
        return gwSpacePassword;
    }

    @Override
    public String getGWSpaceGroups() {
        return gwSpaceGroups;
    }

    @Override
    public String getGWSpaceURL() {
        return gwSpaceURL;
    }

    @Override
    public String getBrandId() {
        return brandId;
    }

    @Override
    public String getMobileCRMUrl() {
        return mobileCRMUrl;
    }

    @Override
    public String getCRMUrl() {
        return crmUrl;
    }

    @Override
    public String getBackOffUsername() {
        return backOfficeUsername;
    }

    @Override
    public String getBackOffPwd() {
        return backOfficePassword;
    }

    @Override
    public String getBackOffVersionURL() {
        return backOfficeVersionUrl;
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    @Override
    public String getDatabaseUsername() {
        return databaseUsername;
    }

    @Override
    public String getDatabasePassword() {
        return databasePassword;
    }
}
