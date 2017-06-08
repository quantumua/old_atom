package com.betamedia.atom.core.api.tp.entities.namingstrategies.customer;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/8/17.
 */

/**
 * Naming strategy for created through the WebSite(UI) new users with default parameters
 */
public class WebSiteNamingStrategy extends AbstractUserNamingStrategy {

    public static final String WEBSITE_REGISTER_EMAIL_TEMPLATE = "Web_{dynamicPart}@24options.atom";

    @Override
    protected String getDefaultFirstName() {
        return DEFAULT_USER_FIRST_NAME;
    }

    @Override
    protected String getDefaultLastName() {
        return DEFAULT_USER_LAST_NAME;
    }

    @Override
    protected String getEmailTemplate() {
        return WEBSITE_REGISTER_EMAIL_TEMPLATE;
    }

    @Override
    protected String getEmailDynamicPartRegex() {
        return DEFAULT_DYNAMIC_EMAIL_PART_REGEX;
    }

    public static UserNamingStrategy get() {
        return new WebSiteNamingStrategy();
    }
}
