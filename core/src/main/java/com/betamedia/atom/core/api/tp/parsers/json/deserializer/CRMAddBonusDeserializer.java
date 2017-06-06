package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * This class is used to deserialize a json node into CRMAddBonus class.
 * It should be used via the {@link JsonDeserialize} annotation.
 *
 * @author Maksym Tsybulskyy
 *         Date: 3/24/17.
 * @see CRMDataDeserializer
 * @see CRMAddBonus
 */
public class CRMAddBonusDeserializer extends CRMDataDeserializer<CRMAddBonus> {

    /**
     * Template method hook for node deserialization
     * @param node a json node, which should not be an array
     * @return deserialization result
     * @throws IOException
     */
    @Override
    protected CRMAddBonus deserialize(JsonNode node) throws IOException {
        return new CRMAddBonus(node.get(CRMAddBonus.BONUS_ID).asText());
    }
}
