package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.betamedia.qe.af.core.api.tp.entities.response.AddBonus.BONUS_ID;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/24/17.
 */
public class AddBonusDeserializer extends CRMDataDeserializer<AddBonus> {
    @Override
    protected AddBonus deserialize(JsonNode node) throws IOException {
        return new AddBonus(node.get(BONUS_ID).asText());
    }
}
