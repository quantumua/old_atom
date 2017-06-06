package com.betamedia.atom.core.api.tp.entities.response;

import com.betamedia.atom.core.api.tp.parsers.json.deserializer.CRMDepositDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
@JsonDeserialize(using = CRMDepositDeserializer.class)
public class CRMDeposit {

    public static final String TRANSACTION_ID = "transactionId";

    private String transactionId;

    @JsonCreator
    public CRMDeposit(@JsonProperty(TRANSACTION_ID) String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "CRMDeposit{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }
}
