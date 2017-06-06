package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * This class is used to deserialize a json node into CRMAccountCreate class.
 * It should be used via the {@link JsonDeserialize} annotation.
 *
 * @author Maksym Tsybulskyy
 *         Date: 3/28/17.
 * @see CRMDataDeserializer
 * @see CRMAccountCreate
 */
public class CRMAccountCreateDeserializer extends CRMDataDeserializer {

    /**
     * Template method hook for node deserialization
     * @param node a json node, which should not be an array
     * @return deserialization result
     * @throws IOException
     */
    @Override
    protected Object deserialize(JsonNode node) throws IOException {
        return new CRMAccountCreate(node.get(CRMAccountCreate.ACCOUNT_ID).asText(), node.get(CRMAccountCreate.ACCOUNT_DISPLAY_ID).asText(),
                node.get(CRMAccountCreate.BRAND_DISPLAY_ID).asText());
    }
}
