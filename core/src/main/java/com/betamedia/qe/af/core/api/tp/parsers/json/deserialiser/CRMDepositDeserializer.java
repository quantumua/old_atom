package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
public class CRMDepositDeserializer extends CRMDataDeserializer<CRMDeposit> {
    @Override
    protected CRMDeposit deserialize(JsonNode node) throws IOException {
        return new CRMDeposit(node.get(CRMDeposit.TRANSACTION_ID).asText());
    }
}
