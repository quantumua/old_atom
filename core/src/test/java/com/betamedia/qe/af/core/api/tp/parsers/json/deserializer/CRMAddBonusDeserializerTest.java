package com.betamedia.qe.af.core.api.tp.parsers.json.deserializer;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.CRMAddBonusDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus.BONUS_ID;
import static org.testng.Assert.assertEquals;

/**
 * Created by Oleksandr Losiev on 4/14/17.
 */
public class CRMAddBonusDeserializerTest {

    private final String bonusId = "4";

    private String validJson;
    private String incompleteJson;

    private ObjectMapper mapper;
    private CRMAddBonusDeserializer deserializer;

    @BeforeMethod
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        deserializer = new CRMAddBonusDeserializer();

        JSONObject incompleteJsonObject = new JSONObject();
        incompleteJson = incompleteJsonObject.toString();

        JSONObject validJsonObject = new JSONObject();
        validJsonObject.put(BONUS_ID, bonusId);
        validJson = validJsonObject.toString();
    }

    @Test
    public void testValidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(validJson);
        Object result = deserializer.deserialize(parser, null);
        CRMAddBonus crmAddBonus = (CRMAddBonus)result;

        assertEquals(bonusId, crmAddBonus.getBonusDisplayId());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIncompleteJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(incompleteJson);
        deserializer.deserialize(parser, null);
    }
}
