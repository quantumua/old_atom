package com.betamedia.qe.af.core.api.tp.entities.response;

import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.AccountCreateCRMDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/28/17.
 */
@JsonDeserialize(using = AccountCreateCRMDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCreateCRM {
    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_DISPLAY_ID = "accountDisplayId";
    public static final String BRAND_DISPLAY_ID = "brandDisplayId";
    private String accountId;
    private String accountDisplayId;
    private String brandDisplayId;

    @JsonCreator
    public AccountCreateCRM(@JsonProperty(ACCOUNT_ID) String accountId, @JsonProperty(ACCOUNT_DISPLAY_ID) String accountDisplayId,
                            @JsonProperty(BRAND_DISPLAY_ID) String brandDisplayId) {
        this.accountId = accountId;
        this.accountDisplayId = accountDisplayId;
        this.brandDisplayId = brandDisplayId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountDisplayId() {
        return accountDisplayId;
    }

    @Override
    public String toString() {
        return "AccountCreateCRM{" +
                "accountId='" + accountId + '\'' +
                ", accountDisplayId='" + accountDisplayId + '\'' +
                ", brandDisplayId='" + brandDisplayId + '\'' +
                '}';
    }
}
