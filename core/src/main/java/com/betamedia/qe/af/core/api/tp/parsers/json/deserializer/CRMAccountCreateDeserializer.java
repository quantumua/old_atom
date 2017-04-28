package com.betamedia.qe.af.core.api.tp.parsers.json.deserializer;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccountCreate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMAccountCreate.*;

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
        return new CRMAccountCreate(node.get(ACCOUNT_ID).asText(), node.get(ACCOUNT_DISPLAY_ID).asText(),
                node.get(BRAND_DISPLAY_ID).asText());
    }
}
