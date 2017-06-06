package com.betamedia.atom.core.api.tp.entities.response;

import com.betamedia.atom.core.api.tp.parsers.json.deserializer.MobileCRMDepositDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Deposit operation response, which holds transaction id.
 *
 * Created by Oleksandr Losiev on 4/21/17.
 */
@JsonDeserialize(using = MobileCRMDepositDeserializer.class)
public class MobileCRMDeposit extends CRMDeposit {
    public static final String TRANSACTION_ID = "Id";

    @JsonCreator
    public MobileCRMDeposit(@JsonProperty(TRANSACTION_ID) String transactionId) {
        super(transactionId);
    }
}
