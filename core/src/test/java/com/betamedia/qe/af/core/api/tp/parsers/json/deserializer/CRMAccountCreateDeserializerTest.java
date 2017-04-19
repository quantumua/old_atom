package com.betamedia.qe.af.core.api.tp.parsers.json.deserializer;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.CRMAccountCreateDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMAccountCreate.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Oleksandr Losiev on 4/14/17.
 */
public class CRMAccountCreateDeserializerTest {

    private final String accountId = "4";
    private final String accountDisplayId = "8";
    private final String brandDisplayId = "2";
    private final String brandDisplayIdFieldName = "brandDisplayId";

    private String validJson;
    private String incompleteJson;
    private ObjectMapper mapper;
    private CRMAccountCreateDeserializer deserializer;

    @BeforeClass
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        deserializer = new CRMAccountCreateDeserializer();

        JSONObject incompleteJsonObject = new JSONObject();
        incompleteJsonObject.put(ACCOUNT_ID, accountId);
        incompleteJson = incompleteJsonObject.toString();

        JSONObject validJsonObject = new JSONObject();
        validJsonObject.put(ACCOUNT_ID, accountId);
        validJsonObject.put(ACCOUNT_DISPLAY_ID, accountDisplayId);
        validJsonObject.put(BRAND_DISPLAY_ID, brandDisplayId);
        validJson = validJsonObject.toString();
    }

    @Test
    public void testValidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(validJson);
        Object result = deserializer.deserialize(parser, null);
        CRMAccountCreate crmAccountCreate = (CRMAccountCreate)result;

        Field brandDisplayField = crmAccountCreate.getClass().getDeclaredField(brandDisplayIdFieldName);
        brandDisplayField.setAccessible(true);
        String actualBrandDisplayId = (String) brandDisplayField.get(crmAccountCreate);

        assertEquals(accountId, crmAccountCreate.getAccountId());
        assertEquals(accountDisplayId, crmAccountCreate.getAccountDisplayId());
        assertEquals(brandDisplayId, actualBrandDisplayId);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIncompleteJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(incompleteJson);
        deserializer.deserialize(parser, null);
    }
}
