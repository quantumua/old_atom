package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.CustomerRegister;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.betamedia.qe.af.core.api.tp.entities.response.CustomerRegister.ACCOUNT_DISPLAY_ID;
import static com.betamedia.qe.af.core.api.tp.entities.response.CustomerRegister.ACCOUNT_ID;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/28/17.
 */
public class CustomerRegisterDeserializer extends CRMDataDeserializer {
    @Override
    protected Object deserialize(JsonNode node) throws IOException {
        return new CustomerRegister(node.get(ACCOUNT_ID).asText(), node.get(ACCOUNT_DISPLAY_ID).asText());
    }
}
