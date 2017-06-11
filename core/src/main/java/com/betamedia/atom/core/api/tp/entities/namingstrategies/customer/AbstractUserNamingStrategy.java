package com.betamedia.atom.core.api.tp.entities.namingstrategies.customer;

import com.betamedia.atom.core.utils.StringUtils;
import com.google.common.base.Strings;

import java.util.Optional;

/**
 * Basic class that incapsulates naming strategy for created with default parameters users according to internal naming convention.
 * It depends on selected way for creating new customer: through CRM Mobile API (REST), CRM Widgets (UI) or WebSite(UI)
 *
 * Defines the naming strategy for newly created customer according to the naming convention.
 * Choose the appropriate implementation according to the way in what the new user will be registered:
 * CRMMobileAPINamingStrategy - through CRM Mobile API (REST)
 * WebSiteNamingStrategy - through WebSite(UI)
 * WidgetsNamingStrategy - CRM Widgets (UI)
 */
 public abstract class AbstractUserNamingStrategy implements UserNamingStrategy {

    public static final String DEFAULT_USER_FIRST_NAME = "Automation";
    public static final String DEFAULT_USER_LAST_NAME = "Test";
    public static final String DEFAULT_DYNAMIC_EMAIL_PART_REGEX = "\\{dynamicPart\\}";
    public static final int CHARS_IN_ID = 6;

    @Override
    public String getFirstName(String firstName) {
        return Optional.ofNullable(firstName)
                .map(Strings::emptyToNull)
                .orElseGet(this::getDefaultFirstName);
    }

    @Override
    public String getLastName(String lastName) {
        return Optional.ofNullable(lastName)
                .map(Strings::emptyToNull)
                .orElseGet(this::getDefaultLastName);
    }

    @Override
    public String getEmail(String email) {
        return Optional.ofNullable(email)
                .map(Strings::emptyToNull)
                .orElseGet(this::getDefaultEmail);
    }

    private String getDefaultEmail() {
        return getEmailTemplate()
                .replaceAll(getEmailDynamicPartRegex(), StringUtils.generateRandomId(CHARS_IN_ID));
    }

    protected abstract String getDefaultFirstName();

    protected abstract String getDefaultLastName();

    protected abstract String getEmailTemplate();

    protected abstract String getEmailDynamicPartRegex();

}
