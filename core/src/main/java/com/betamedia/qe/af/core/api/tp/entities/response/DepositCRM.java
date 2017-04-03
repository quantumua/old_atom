package com.betamedia.qe.af.core.api.tp.entities.response;

import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.CRMDepositDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
@JsonDeserialize(using = CRMDepositDeserializer.class)
public class DepositCRM {

    public static final String TRANSACTION_ID = "transactionId";

    private String transactionId;

    @JsonCreator
    public DepositCRM(@JsonProperty(TRANSACTION_ID) String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "DepositCRM{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }
}
