package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * This class is used to deserialize a json node into CRMDeposit class.
 * It should be used via the {@link JsonDeserialize} annotation.
 *
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 * @see CRMDataDeserializer
 * @see CRMDeposit
 */
public class CRMDepositDeserializer extends CRMDataDeserializer<CRMDeposit> {

    /**
     * Template method hook for node deserialization
     * @param node a json node, which should not be an array
     * @return deserialization result
     * @throws IOException
     */
    @Override
    protected CRMDeposit deserialize(JsonNode node) throws IOException {
        return new CRMDeposit(node.get(CRMDeposit.TRANSACTION_ID).asText());
    }
}
