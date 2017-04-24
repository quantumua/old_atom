package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.MobileCRMDeposit;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Deserializer for MobileCRMDeposit.
 * @see MobileCRMDeposit
 */
public class MobileCRMDepositDeserializer extends CRMDataDeserializer<MobileCRMDeposit> {

    @Override
    protected MobileCRMDeposit deserialize(JsonNode node) throws IOException {
        return new MobileCRMDeposit(node.get(MobileCRMDeposit.TRANSACTION_ID).asText());
    }
}
