package com.betamedia.qe.af.core.api.tp.parsers.json.deserializer;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus.BONUS_ID;

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
        return new CRMAddBonus(node.get(BONUS_ID).asText());
    }
}
