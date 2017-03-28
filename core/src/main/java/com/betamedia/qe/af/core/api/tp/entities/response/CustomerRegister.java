package com.betamedia.qe.af.core.api.tp.entities.response;

import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.CustomerRegisterDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/28/17.
 */
@JsonDeserialize(using = CustomerRegisterDeserializer.class)
public class CustomerRegister {
    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_DISPLAY_ID = "accountDisplayId";
    private String accountId;
    private String accountDisplayId;

    @JsonCreator
    public CustomerRegister(@JsonProperty(ACCOUNT_ID) String accountId, @JsonProperty(ACCOUNT_DISPLAY_ID) String accountDisplayId) {
        this.accountId = accountId;
        this.accountDisplayId = accountDisplayId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountDisplayId() {
        return accountDisplayId;
    }

    @Override
    public String toString() {
        return "CustomerRegister{" +
                "accountId='" + accountId + '\'' +
                ", accountDisplayId='" + accountDisplayId + '\'' +
                '}';
    }
}
