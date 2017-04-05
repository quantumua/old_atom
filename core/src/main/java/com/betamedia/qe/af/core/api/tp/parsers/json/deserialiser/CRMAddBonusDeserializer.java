package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus.BONUS_ID;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/24/17.
 */
public class CRMAddBonusDeserializer extends CRMDataDeserializer<CRMAddBonus> {
    @Override
    protected CRMAddBonus deserialize(JsonNode node) throws IOException {
        return new CRMAddBonus(node.get(BONUS_ID).asText());
    }
}
