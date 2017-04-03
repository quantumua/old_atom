package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.DepositCRM;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
public class CRMDepositDeserializer extends CRMDataDeserializer<DepositCRM> {
    @Override
    protected DepositCRM deserialize(JsonNode node) throws IOException {
        return new DepositCRM(node.get(DepositCRM.TRANSACTION_ID).asText());
    }
}
